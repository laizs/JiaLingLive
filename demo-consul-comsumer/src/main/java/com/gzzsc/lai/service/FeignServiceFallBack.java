package com.gzzsc.lai.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @className FeignServiceFallBack
 * @Deacription TODO
 * @Author laizs
 * @Date 2022/1/21 10:38
 **/
@Component
public class FeignServiceFallBack implements FeignService {
    @Override
    public String sleep(int sec) {
        return "这里是FallBack："+sec;
    }
}
