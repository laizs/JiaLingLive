package com.gzzsc.lai.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName ProviderController
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/7/15 14:46
 **/
@RestController
@Slf4j
public class ProviderController {
    @Autowired
    private DiscoveryClient discoveryClient;
    @GetMapping("/hello")
    public String hello(){
        List<String> services=discoveryClient.getServices();
        for(String s:services){
            log.info("hello service:{}",s);
        }
        log.info("休眠5秒................");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello spring cloud";
    }
    @GetMapping("/nice")
    public String nice(){
        List<String> services=discoveryClient.getServices();
        for(String s:services){
            log.info("nice service:{}",s);
        }
        System.out.println("test dev...");
        log.info("休眠5秒................");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "nice to meet you";
    }
}
