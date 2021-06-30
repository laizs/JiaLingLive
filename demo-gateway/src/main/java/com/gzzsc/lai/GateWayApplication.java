package com.gzzsc.lai;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @className GateWayApplication
 * @Deacription SpringClould网关项目
 * @Author laizs
 * @Date 2021/4/12 15:39
 **/
@SpringBootApplication
public class GateWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class,args);
    }
}
