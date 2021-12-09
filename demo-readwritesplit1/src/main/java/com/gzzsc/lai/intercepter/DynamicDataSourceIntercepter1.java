package com.gzzsc.lai.intercepter;

import com.gzzsc.lai.cfg.DBContextHolder;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import java.util.Locale;
import java.util.Properties;

/**
 * @className DynamicDataSourceIntercepter
 * @Deacription mybatis插件，拦截器，根据sql进行动态数据源切换
 * @Author laizs
 * @Date 2021/12/8 17:09
 **/

@Intercepts({@Signature(type = Executor.class,method = "update",args = {MappedStatement.class,Object.class}),
        @Signature(type = Executor.class,method = "query",args = {MappedStatement.class,Object.class, RowBounds.class, ResultHandler.class})})
public class DynamicDataSourceIntercepter1 implements Interceptor {
    private final static Logger logger= LoggerFactory.getLogger(DynamicDataSourceIntercepter1.class);
    private static String REGEX=".*insert\\u0020.*|.*update\\u0020.*|.*delete.*";
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //判断是否是新事务，如果是新事务，则需要把事务属性存放到当前线程中
        boolean synchronizationActive= TransactionSynchronizationManager.isActualTransactionActive();
        //获取mybatis转换过来的CRUD参数
        Object[] objects=invocation.getArgs();
        System.out.println("转换过后的参数------"+objects);
        //MappedStatement维护了一条<select|update|delete|insert>节点的封装
        MappedStatement mappedStatement= (MappedStatement) objects[0];
        //默认主库写入操作
        if(!synchronizationActive){//没有事务
            /**
             * SqlCommandType.SELECT Sql的类型  select|update|insert|delete
             */
            if(mappedStatement.getSqlCommandType().equals(SqlCommandType.SELECT)){
                /**
                 * BoundSql表示动态生成的SQL语句以及相应的参数信息
                 * getSqlSource()  sql对象中对应的sql语句
                 */
                BoundSql boundSql=mappedStatement.getSqlSource().getBoundSql(objects[1]);
                //将字符装换为简体中文的小写
                String sql = boundSql.getSql().toLowerCase(Locale.CHINA).replaceAll("\\t\\n\\r", " ");
                if (sql.matches(REGEX)){//有dml操作
                    System.out.println("有dml操作，走主库");
                    DBContextHolder.master();
                }else {
                    System.out.println("纯查询操作，走主库");
                    DBContextHolder.slave();
                }
            }

        }else{
            System.out.println("当前操作有事务，走主库");
            DBContextHolder.master();
        }
        logger.info("设置方法为 [{}] 使用的是 [{}], sql类型SqlCommandType [{}].. ",mappedStatement.getId(),DBContextHolder.get(),mappedStatement.getSqlCommandType().name());
        //执行拦截对象真正的方法
        return invocation.proceed();
    }
    /**
     * 包装目标对象 为目标对象创建动态代理
     * MyBatis执行器，是MyBatis 调度的核心，负责SQL语句的生成和查询缓存的维护
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        System.out.println("目标对象------"+target);
        if (target instanceof Executor){
            System.out.println("创建的代理对象------"+target);
            return Plugin.wrap(target,this);
        }else {
            return target;
        }

    }
    @Override
    public void setProperties(Properties properties) {

    }
}
