package com.gzzsc.lai.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className HystrixProviderController
 * @Deacription 测试Hystrix特性的服务提供方
 * @Author laizs
 * @Date 2021/3/15 13:21
 **/
@RestController
public class HystrixProviderController {
    @GetMapping("/sleep/{sec}")
    public String sleep(@PathVariable(name = "sec") int sec){
        try {
            Thread.sleep(sec*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "sleep success:"+sec+"秒";
    }
}
