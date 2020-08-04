package com.gzzsc.lai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName EurekaClienterApplication
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/7/15 15:26
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class EurekaClienterApplication {
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
    public static void main(String[] args) {
        SpringApplication.run(EurekaClienterApplication.class,args);
    }
}
