package com.gzzsc.lai.cfg;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @className MyBatisConfig
 * @Deacription mybatis的配置
 * @Author laizs
 * @Date 2021/12/7 10:16
 **/
@Configuration
//由于使用mysqlbatis，是要此注解扫描mybatis的mapper类
@MapperScan("com.gzzsc.lai.mapper")
@EnableTransactionManagement //如果mybatis中service实现类中加入事务注解，需要此处添加该注解
public class MyBatisConfig {
    @Resource(name="myRountintDataSource")
    private DataSource myRoutingDataSource;
    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;
    @Value("${mybatis.config-location}")
    private String configLocation;
    //会依赖注入Spring容器中所有的mybatis的Interceptor拦截器
    @Autowired(required = false)
    private Interceptor[] interceptors;
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(myRoutingDataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
        sqlSessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(configLocation));
        sqlSessionFactoryBean.setPlugins(interceptors);

        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 定义事务处理器
     * @return
     */
    @Bean
    public PlatformTransactionManager platformTransactionManager(){
        return new DataSourceTransactionManager(myRoutingDataSource);
    }
}
