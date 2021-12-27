package com.plf.speed;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Random;

/**
 * @author panlf
 * @date 2021/12/19
 */
public class InsertData {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Random random = new Random();
        String[] str = new String[]{"越城区","诸暨市","上虞区","新昌县","柯桥区","嵊州市"};

        String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&useSSL=true&serverTimezone=Asia/Shanghai";
        String username = "root";
        String password = "root";
        Connection conn = DriverManager.getConnection(url, username, password);

        PreparedStatement preparedStatement = null;

        for(int i=200000;i<=200000;i++){
            int size = random.nextInt(6);
            int age = random.nextInt(100);
            double price = random.nextDouble()*6000;
            String name = "000000PLF00"+i;
            String sql = String.format("insert into user_info(id,age,name,createtime,price,address) values(%d,%d,'%s','%s',%f,'%s')",
                    i,age,name,"2021-12-19",price,str[size]);
            System.out.println(sql);
            preparedStatement =  conn.prepareStatement(sql);
            preparedStatement.execute();
        }

        conn.close();
    }
}
