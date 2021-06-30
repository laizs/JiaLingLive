package com.gzzsc.lai;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @className SpringBootAdminApplication
 * @Deacription 启动类
 * @Author laizs
 * @Date 2021/3/22 10:36
 **/
@EnableAdminServer
@SpringBootApplication

public class SpringBootAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootAdminApplication.class,args);
    }
}
