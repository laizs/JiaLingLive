package com.gzzsc.lai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @className ConsulProviderApplication
 * @Deacription TODO
 * @Author laizs
 * @Date 2021/3/11 11:35
 **/
@SpringBootApplication
//开启服务注册服务
@EnableDiscoveryClient
public class ConsulProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsulProviderApplication.class,args);
    }
}
