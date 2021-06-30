package com.gzzsc.lai.controller.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

/**
 * @className ChainService
 * @Deacription 测试链路情况
 * @Author laizs
 * @Date 2021/3/19 10:52
 **/
@Service
public class ChainService {
    @SentinelResource
    public void chainTest(){
        System.out.println("测试链路情况");
    }
}
