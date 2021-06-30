package com.gzzsc.lai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @className ConsulComsumerApplication
 * @Deacription 消费者启动类
 * @Author laizs
 * @Date 2021/3/11 13:22
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ConsulComsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsulComsumerApplication.class,args);
    }
    @Bean
    @LoadBalanced
    public RestTemplate rebbonRestTemplate(){
        return new RestTemplate();
    }
}
