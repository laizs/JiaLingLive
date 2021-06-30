package com.gzzsc.lai.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @className NotFault404Controller
 * @Deacription 404
 * @Author laizs
 * @Date 2021/4/13 9:56
 **/
@RestController
public class NotFault404Controller {
    @RequestMapping("/notfound")
    public Mono<Map> notFault(){
        Map<String,String> stringMap=new HashMap<>();
        stringMap.put("code","404");
        stringMap.put("data","Not Fault");
        return Mono.just(stringMap);
    }

}
