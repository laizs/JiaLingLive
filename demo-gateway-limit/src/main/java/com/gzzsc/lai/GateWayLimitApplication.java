package com.gzzsc.lai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

/**
 * @className GateWayLimitApplication
 * @Deacription 启动类
 * @Author laizs
 * @Date 2021/4/13 13:36
 **/
@SpringBootApplication
public class GateWayLimitApplication {
    public static void main(String[] args) {
        SpringApplication.run(GateWayLimitApplication.class,args);
    }
    @Bean
    public KeyResolver ipKeyResolver(){
        //根据ip地址限流
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }
}
