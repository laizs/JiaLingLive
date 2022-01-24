package com.gzzsc.lai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @className TurbineConsulDemoApplication
 * @Deacription TODO
 * @Author laizs
 * @Date 2022/1/21 16:52
 **/
@SpringBootApplication
@EnableHystrix
@EnableHystrixDashboard
@EnableDiscoveryClient
@EnableTurbine
public class TurbineConsulDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(TurbineConsulDemoApplication.class,args);
    }

}
