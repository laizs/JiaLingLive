package com.gzzsc.lai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ClassName EurekaServerApplication
 * @Deacription eureka server集群启动类
 * @Author laizs
 * @Date 2020/7/15 13:28
 **/
@SpringBootApplication
//开启eurekaServer支持，即作为eureka注册中心服务端
@EnableEurekaServer
//开启
@EnableEurekaClient
public class EurekaCluterApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaCluterApplication.class,args);
    }
}
