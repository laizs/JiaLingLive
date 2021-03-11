package com.gzzsc.lai.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className HelloController
 * @Deacription TODO
 * @Author laizs
 * @Date 2021/3/11 11:38
 **/
@RestController
public class HelloController {
    /**
     * 服务者名称
     */
    @Value("${provider.name}")
    private String name;
    /**
     * 服务端口
     */
    @Value("${server.port}")
    private String port;
    @RequestMapping("/hello")
    private String hello(){
        String str="provider:"+name+" port:"+port;
        return str;
    }
}
