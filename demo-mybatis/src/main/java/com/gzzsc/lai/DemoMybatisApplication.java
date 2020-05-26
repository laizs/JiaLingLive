package com.gzzsc.lai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName DemoMybatisApplication
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/5/26 10:12
 **/
@SpringBootApplication
//由于使用mysqlbatis，是要此注解扫描mybatis的mapper类
@MapperScan("com.gzzsc.lai.mapper")
@EnableTransactionManagement //如果mybatis中service实现类中加入事务注解，需要此处添加该注解
public class DemoMybatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoMybatisApplication.class,args);
    }
}
