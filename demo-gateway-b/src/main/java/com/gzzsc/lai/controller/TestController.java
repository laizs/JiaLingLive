package com.gzzsc.lai.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className TestController
 * @Deacription controller
 * @Author laizs
 * @Date 2021/4/12 16:12
 **/
@RestController
public class TestController {
    @GetMapping("/a")
    public String a(@RequestParam(required = false) String a){
        return "参数:"+a;
    }
    @GetMapping("/hystrix")
    public String hystrix(@RequestParam(required = false) String a){
        return "hystrix参数:"+a;
    }
    @GetMapping("/test-ip")
    public String testIp(@RequestParam(required = false) String a){
        return "test-ip:"+a;
    }
}
