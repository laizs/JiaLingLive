package com.gzzsc.lai.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @className FallbackController
 * @Deacription fallback
 * @Author laizs
 * @Date 2021/4/13 11:51
 **/
@RestController
public class FallbackController {
    @RequestMapping("/fallback")
    public Mono<Map<String,String>> fallback(){
        Map<String,String> stringMap=new HashMap<>();
        stringMap.put("code","100");
        stringMap.put("data","Service Not Available");
        return Mono.just(stringMap);
    }
}
