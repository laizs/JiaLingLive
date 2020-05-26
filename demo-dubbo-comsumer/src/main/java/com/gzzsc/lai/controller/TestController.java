package com.gzzsc.lai.controller;

import com.gzzsc.lai.dubbo.impl.HelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/5/19 17:33
 **/
@RestController
public class TestController {
    @Reference(check = false)
    private HelloService helloService;
    @RequestMapping("/test")
    public String test(){
        String msg=this.helloService.hello("tiger");
        return msg;
    }
}
