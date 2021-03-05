package com.gzzsc.lai;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ClassName EurekaServerApplication
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/7/15 13:28
 **/
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    private static  final Logger log= LoggerFactory.getLogger(EurekaServerApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class,args);
        while (true){
            log.info("一段没什么意思的日志");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
