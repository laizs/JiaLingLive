package com.gzzsc.lai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

/**
 * @className ReadWriteDemoApplication
 * @Deacription TODO
 * @Author laizs
 * @Date 2021/12/6 17:56
 **/
@SpringBootApplication
public class ReadWriteDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReadWriteDemoApplication.class,args);
    }
}
