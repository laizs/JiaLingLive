package com.gzzsc.lai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName DemoDubboComsumerApplication
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/5/19 17:16
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class DemoDubboComsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoDubboComsumerApplication.class);
    }
}
