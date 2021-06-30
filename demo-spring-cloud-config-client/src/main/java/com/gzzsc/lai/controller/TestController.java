package com.gzzsc.lai.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className TestController
 * @Deacription TODO
 * @Author laizs
 * @Date 2021/3/16 16:29
 **/
@RestController
public class TestController {
    @Value("${app.version}")
    private String appVersion;
    @Value("${server.port}")
    private String port;
    @GetMapping("/getConfig")
    public String getConfig(){
        String s=this.appVersion+":"+this.port;
        return s;
    }
}
