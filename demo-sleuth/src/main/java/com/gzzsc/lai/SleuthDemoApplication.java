package com.gzzsc.lai;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className SluethDemoApplication
 * @Deacription d
 * @Author laizs
 * @Date 2021/4/14 14:01
 **/
@SpringBootApplication
@RestController
public class SleuthDemoApplication {
    private static final Logger log= LoggerFactory.getLogger(SleuthDemoApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(SleuthDemoApplication.class,args);
    }
    @RequestMapping("/hello")
    public String home(){
        log.info("处理hello页");
       return "Hello World";
    }
}
