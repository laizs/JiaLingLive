package com.gzzsc.lai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName DemoEsRestClientApplication
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/9/9 17:00
 **/
@SpringBootApplication
@MapperScan("com.gzzsc.lai.mapper")
@EnableTransactionManagement //如果mybatis中service实现类中加入事务注解，需要此处添加该注解
public class DemoEsRestClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoEsRestClientApplication.class,args);
    }
}
