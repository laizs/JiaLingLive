package com.gzzsc.lai;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className ZipkinDemoApplication
 * @Deacription d
 * @Author laizs
 * @Date 2021/4/15 16:11
 **/
@SpringBootApplication
@RestController
public class ZipkinDemoApplication {
    private final  static Logger log= LoggerFactory.getLogger(ZipkinDemoApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(ZipkinDemoApplication.class,args);
    }
    @RequestMapping("/hello")
    public String home(){
        log.info("处理hello页");
        return "Hello World";
    }
}
