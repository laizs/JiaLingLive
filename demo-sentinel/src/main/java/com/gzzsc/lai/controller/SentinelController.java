package com.gzzsc.lai.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className SentinelController
 * @Deacription controller
 * @Author laizs
 * @Date 2021/3/19 10:09
 **/
@RestController
public class SentinelController {
    @SentinelResource("hello")
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
}
