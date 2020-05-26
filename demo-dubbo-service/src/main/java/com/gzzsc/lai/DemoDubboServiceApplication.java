package com.gzzsc.lai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName DemoDubboServiceApplication
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/5/19 14:00
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class DemoDubboServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoDubboServiceApplication.class);
    }
}
