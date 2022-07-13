package com.plf.boot.plugin.config;

import cn.hutool.core.util.ClassUtil;
import com.baomidou.mybatisplus.extension.parser.JsqlParserSupport;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.plf.boot.plugin.annotations.EnableTableScan;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.util.ClassUtils;


/**
 * @author panlf
 * @date 2022/7/13
 */
@Intercepts(
        {
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class})
        }
)
@Slf4j
public class MyBatisFullTableSearchInterceptor extends JsqlParserSupport implements Interceptor {

    private static final int MAX_PAGE_SIZE = 1000;

    @Override
    protected void processSelect(Select select, int index, String sql, Object obj) {
        PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
        Expression where = plainSelect.getWhere();

        if(where == null){
            throw new RuntimeException("全表扫描! SQL语句没有Where条件");
        }

        if(where instanceof EqualsTo){
            Expression left =  ((EqualsTo)where).getLeftExpression();
            Expression right =  ((EqualsTo)where).getRightExpression();
            if(left.getClass().isAssignableFrom(right.getClass())){
                if(left instanceof StringValue &&
                        ((StringValue)left).getValue().equals(((StringValue)right).getValue())){
                    throw new RuntimeException("非法SQL，where 跟随恒等式");
                }

                if(left instanceof LongValue &&
                        ((LongValue)left).getValue() == (((LongValue)right).getValue())){
                    throw new RuntimeException("非法SQL，where 跟随恒等式");
                }
            }
        }
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object params = invocation.getArgs()[1];
        String id = mappedStatement.getId();
        int index = id.lastIndexOf(".");
        String mapperClassName = id.substring(0,index);
        log.info("Full Table intercept mapperClassName {} ",mapperClassName);
        //这里存疑
        Class<?> clazz = ClassUtils.resolveClassName(mapperClassName,this.getClass().getClassLoader());
        EnableTableScan enableTableScan = clazz.getAnnotation(EnableTableScan.class);
        if(enableTableScan != null && enableTableScan.enable()){
            return invocation.proceed();
        }

        Page<Object> localPage = PageHelper.getLocalPage();
        BoundSql boundSql = mappedStatement.getBoundSql(params);
        if(localPage == null){
            parserSingle(boundSql.getSql(),new Object());
        } else {
            int pageSize = localPage.getPageSize();
            boolean count = localPage.isCount();
            if(count){
                parserSingle(boundSql.getSql(),new Object());
            }

            if(pageSize > MAX_PAGE_SIZE){
                throw new RuntimeException("Page over "+ MAX_PAGE_SIZE);
            }
        }

        return invocation.proceed();
    }
}
