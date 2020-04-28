package com.gzzsc.lai.controller;

import com.gzzsc.lai.controller.bean.MyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Deacription 测试
 * @Author laizs
 * @Date 2020/4/28 16:24
 **/
@RestController
@RequestMapping("/test")
@RefreshScope //自动刷新属性配置
public class TestController {
    @Value("${cache:false}")
    private boolean cache;
    @Autowired
    private MyProperties myProperties;
    @GetMapping("/cache")
    public boolean getCache(){
        return cache;
    }
    @GetMapping("/pro")
    public MyProperties getPro(){
        return myProperties;
    }
}
