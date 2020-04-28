package com.gzzsc.lai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName DemoMyBatisPlusApplication
 * @Deacription 主类
 * @Author laizs
 * @Date 2020/4/27 17:03
 **/
@SpringBootApplication
@MapperScan("com.gzzsc.lai.mapper")
public class DemoMyBatisPlusApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoMyBatisPlusApplication.class,args);
    }
}
