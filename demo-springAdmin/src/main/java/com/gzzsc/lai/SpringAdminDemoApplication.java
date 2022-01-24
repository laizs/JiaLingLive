package com.gzzsc.lai;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @className SpringAdminDemoApplication
 * @Deacription TODO
 * @Author laizs
 * @Date 2022/1/19 15:58
 **/
@SpringBootApplication
@EnableAdminServer
public class SpringAdminDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringAdminDemoApplication.class,args);
    }
}
