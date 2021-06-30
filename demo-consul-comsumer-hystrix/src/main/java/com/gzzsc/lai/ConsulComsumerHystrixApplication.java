package com.gzzsc.lai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @className ConsulComsumerHystrixApplication
 * @Deacription TODO
 * @Author laizs
 * @Date 2021/3/12 15:20
 **/
@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableDiscoveryClient
public class ConsulComsumerHystrixApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsulComsumerHystrixApplication.class,args);
    }
    @Bean
    @LoadBalanced
    public RestTemplate rebbonRestTemplate(){
        return new RestTemplate();
    }
}
