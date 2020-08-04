package com.gzzsc.lai.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @ClassName HelloServiceFallBack
 * @Deacription HelloService服务降级FallBack类
 * @Author laizs
 * @Date 2020/7/21 14:33
 **/
@Component
public class HelloServiceFallBack implements HelloService {
    @Override
    public String hello() {
        return "###fallback--hello";
    }

    @Override
    public String nice() {
        return "###fallback--nice";
    }
}
