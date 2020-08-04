package com.gzzsc.lai.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName HelloService
 * @Deacription hello
 * @FeignClient中eurekaProvider是服务提供者的application.name
 * @Author laizs
 * @Date 2020/7/15 15:49
 **/
@FeignClient(value = "eureka-provider",path = "/eurekaProvider")
public interface HelloService {
    @RequestMapping(value="/hello")
    String hello();
    @RequestMapping(value="/nice")
    String nice();
}
