package com.plf.speed;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.StopWatch;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author panlf
 * @date 2021/12/26
 */
public class TransData {
    static TransData transData = new TransData();
    public static void main(String[] args) {
        /*List<Map<String,String>> columnList = null;
        // Java 自动关闭资源
        try(Connection connection = transData.getMySQLConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&useSSL=true&serverTimezone=Asia/Shanghai",
                "root",
                "root")){
            columnList = transData.getColumn(connection,"test","user_info");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    */
        try {
            transData.transThirdSQLData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    //排除字段的
    public void transOneSQLData() throws Exception{
        Connection selectConnection = transData.getMySQLConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai",
                "root",
                "root");

        Connection insertConnection = transData.getMySQLConnection("jdbc:mysql://localhost:3306/test_after?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai",
                "root",
                "root");
        PreparedStatement preparedStatement = selectConnection.prepareStatement("select * from user_info", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setFetchSize(1000);
        ResultSet  resultSet = preparedStatement.executeQuery();
        String sql = "insert into user_info_1(id,age,name,createtime,price,address) values(?,?,?,?,?,?)";
        PreparedStatement insertStatement = insertConnection.prepareStatement(sql);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        while(resultSet.next()){
            insertStatement.setInt(1,resultSet.getInt("id"));
            insertStatement.setInt(2,resultSet.getInt("age"));
            insertStatement.setString(3,resultSet.getString("name"));
            insertStatement.setTimestamp(4,resultSet.getTimestamp("createtime"));
            insertStatement.setDouble(5,resultSet.getDouble("price"));
            insertStatement.setString(6,resultSet.getString("address"));
            insertStatement.execute();
        }
        stopWatch.stop();
        //cost time ===>313.9721336
        System.out.println("cost time ===>"+stopWatch.getTotalTimeSeconds());
    }


    public void transTwoSQLData() throws Exception{
        Connection selectConnection = transData.getMySQLConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai",
                "root",
                "root");

        Connection insertConnection = transData.getMySQLConnection("jdbc:mysql://localhost:3306/test_after?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true",
                "root",
                "root");
        PreparedStatement preparedStatement = selectConnection.prepareStatement("select * from user_info", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setFetchSize(1000);
        ResultSet  resultSet = preparedStatement.executeQuery();
        String sql = "insert into user_info_2(id,age,name,createtime,price,address) values(?,?,?,?,?,?)";
        PreparedStatement insertStatement = insertConnection.prepareStatement(sql);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        int i = 0;
        while(resultSet.next()){
            insertStatement.setInt(1,resultSet.getInt("id"));
            insertStatement.setInt(2,resultSet.getInt("age"));
            insertStatement.setString(3,resultSet.getString("name"));
            insertStatement.setTimestamp(4,resultSet.getTimestamp("createtime"));
            insertStatement.setDouble(5,resultSet.getDouble("price"));
            insertStatement.setString(6,resultSet.getString("address"));

            //1.sql语句放入batch
            insertStatement.addBatch();
            i++;
            if (i % 500 == 0){
                //2.执行Batch
                insertStatement.executeBatch();
                //3.清空Batch
                insertStatement.clearBatch();
            }
        }
        //将剩余的执行
        insertStatement.executeBatch();
        insertStatement.clearBatch();

        stopWatch.stop();
        //cost time ===>5.8604255
        System.out.println("cost time ===>"+stopWatch.getTotalTimeSeconds());
    }

    public void transThirdSQLData() throws Exception{
        Connection selectConnection = transData.getMySQLConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai",
                "root",
                "root");

        Connection insertConnection = transData.getMySQLConnection("jdbc:mysql://localhost:3306/test_after?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true&useServerPrepStmts=false",
                "root",
                "root");

        PreparedStatement preparedStatement = selectConnection.prepareStatement("select * from user_info", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setFetchSize(1000);
        ResultSet  resultSet = preparedStatement.executeQuery();
        String sql = "insert into user_info_3(id,age,name,createtime,price,address) values(?,?,?,?,?,?)";
        PreparedStatement insertStatement = insertConnection.prepareStatement(sql);
        //设置不允许自动提交数据
        insertConnection.setAutoCommit(false);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        int i = 0;
        while(resultSet.next()){
            insertStatement.setInt(1,resultSet.getInt("id"));
            insertStatement.setInt(2,resultSet.getInt("age"));
            insertStatement.setString(3,resultSet.getString("name"));
            insertStatement.setTimestamp(4,resultSet.getTimestamp("createtime"));
            insertStatement.setDouble(5,resultSet.getDouble("price"));
            insertStatement.setString(6,resultSet.getString("address"));

            //1.sql语句放入batch
            insertStatement.addBatch();
            i++;
            if (i % 500 == 0){
                //2.执行Batch
                insertStatement.executeBatch();
                //3.清空Batch
                insertStatement.clearBatch();
            }
        }
        //将剩余的执行
        insertStatement.executeBatch();
        insertStatement.clearBatch();
        insertConnection.commit();
        stopWatch.stop();
        //cost time ===>5.3574054
        System.out.println("cost time ===>"+stopWatch.getTotalTimeSeconds());
    }

    public List<Map<String,String>> getColumn(Connection connection,
                                              String dataBase,
                                              String tableName) {
        List<Map<String,String>> columnList = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {

            String sql = String.format("SELECT COLUMN_NAME,DATA_TYPE FROM " +
                    " information_schema.COLUMNS " +
                    " WHERE TABLE_SCHEMA = '%s' and TABLE_NAME='%s'",dataBase,tableName);
            preparedStatement = connection.prepareStatement(sql);
            resultSet =  preparedStatement.executeQuery();

            while(resultSet.next()){
                Map<String,String> column = new HashMap<>();
                String columnName =  resultSet.getString("COLUMN_NAME");
                String dataType = resultSet.getString("DATA_TYPE");
                column.put(columnName,dataType);
                columnList.add(column);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(preparedStatement,resultSet);
        }

        return columnList;
    }


    /**
     * 获取连接
     * @param url 地址
     * @param username 用户名
     * @param password 密码
     * @return 返回连接
     */
    public Connection getMySQLConnection(String url,String username,String password){
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 关闭资源
     * @param args 需关闭的资源
     */
    public void close(AutoCloseable...args) {
        if(args!=null && args.length > 0){
            for(AutoCloseable a:args){
                if(a!=null){
                    try {
                        a.close();
                        System.out.println(a.getClass().getName()+"is close!");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
