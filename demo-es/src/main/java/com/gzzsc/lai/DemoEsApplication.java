package com.gzzsc.lai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName DemoEsApplication
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/9/2 10:53
 **/
@SpringBootApplication
@EnableElasticsearchRepositories
//由于使用mysqlbatis，是要此注解扫描mybatis的mapper类
@MapperScan("com.gzzsc.lai.mapper")
@EnableTransactionManagement //如果mybatis中service实现类中加入事务注解，需要此处添加该注解
public class DemoEsApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoEsApplication.class,args);

    }
    @Bean
    @ConditionalOnProperty("initial-import.enabled")
    public SampleDataSet dataSet(){
        return new SampleDataSet();
    }
}
