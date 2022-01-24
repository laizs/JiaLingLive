package com.gzzsc.lai.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className HealthCheckController
 * @Deacription TODO
 * @Author laizs
 * @Date 2022/1/20 11:46
 **/
@RestController
public class HealthCheckController {
    private final static Logger logger= LoggerFactory.getLogger(HealthCheckController.class);
    @RequestMapping("/myHealth")
    public String check(){
        logger.info("--健康检查--");
        try {
            //测试健康检查超时
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "false";
    }
}
