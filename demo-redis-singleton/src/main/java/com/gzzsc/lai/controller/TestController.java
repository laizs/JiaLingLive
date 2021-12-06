package com.gzzsc.lai.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className TestController
 * @Author laizs
 * @Date 2021/11/11 17:00
 **/
@RestController
public class TestController {
    private final Logger logger= LoggerFactory.getLogger(TestController.class);
    @Autowired
    private RedisTemplate redisTemplate;
    @GetMapping("/test")
    public String test(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i=0;
                while(true){
                    ++i;
                    redisTemplate.opsForValue().set("abc","abc"+i);
                    logger.info("Set abc----");
                    logger.info("Get abc:"+redisTemplate.opsForValue().get("abc"));
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        logger.info("！！！异常:",e);
                    }
                }

            }
        }).start();
        return "success";
    }
}
