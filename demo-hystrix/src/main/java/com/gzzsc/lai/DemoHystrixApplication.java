package com.gzzsc.lai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName DemoHystrixApplication
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/7/20 16:49
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrixDashboard
public class DemoHystrixApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoHystrixApplication.class,args);
    }
    /**
     * 注入 RestTemplate
     * 并用@LoadBalanced注解，用负载均衡策略请求服务提供者
     * 这是Spring ribbon提供的能力
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
