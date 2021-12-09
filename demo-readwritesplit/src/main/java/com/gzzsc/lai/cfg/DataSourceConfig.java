package com.gzzsc.lai.cfg;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @className DataSourceConfig
 * @Deacription 数据源配置
 * @Author laizs
 * @Date 2021/12/7 9:43
 **/
@Configuration
public class DataSourceConfig {
    @Bean
    @ConfigurationProperties("spring.datasource.master")
    public DataSource masterDataSource(){
        return DataSourceBuilder.create().build();
    }
    @Bean
    @ConfigurationProperties("spring.datasource.slave1")
    public DataSource slave1DataSource(){
        return DataSourceBuilder.create().build();
    }
    @Bean
    @ConfigurationProperties("spring.datasource.slave2")
    public DataSource slave2DataSource(){

        return DataSourceBuilder.create().build();
    }

    /**
     * mybatis的SqlSessionFactory最终使用的数据源
     * @param masterDataSource
     * @param slave1DataSource
     * @param slave2DataSource
     * @return
     */
    @Bean
    public DataSource myRountintDataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
                                           @Qualifier("slave1DataSource") DataSource slave1DataSource,
                                           @Qualifier("slave2DataSource") DataSource slave2DataSource){
        Map<Object,Object> targetDataSource=new HashMap<>();
        targetDataSource.put(DBTypeEnum.MASTER,masterDataSource);
        targetDataSource.put(DBTypeEnum.SLAVE1,slave1DataSource);
        targetDataSource.put(DBTypeEnum.SLAVE2,slave2DataSource);
        MyRoutingDataSource myRoutingDataSource=new MyRoutingDataSource();
        myRoutingDataSource.setTargetDataSources(targetDataSource);
        myRoutingDataSource.setDefaultTargetDataSource(masterDataSource);
        return myRoutingDataSource;

    }
    @Bean
    public  JdbcTemplate JdbcTemplate(DataSource myRountintDataSource){
        JdbcTemplate jdbcTemplate=new JdbcTemplate();
        jdbcTemplate.setDataSource(myRountintDataSource);
        return jdbcTemplate;
    }
}
