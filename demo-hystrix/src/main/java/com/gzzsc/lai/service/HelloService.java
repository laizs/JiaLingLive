package com.gzzsc.lai.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName HelloService
 * @Deacription 测试服务调用
 * @FeignClient中eurekaProvider是服务提供者的application.name
 * @Author laizs
 * @Date 2020/7/21 14:28
 **/
@FeignClient(value = "eureka-provider",path = "/eurekaProvider",fallback =HelloServiceFallBack.class )
public interface HelloService {
    @RequestMapping(value="/hello")
    public String hello();
    @RequestMapping(value="/nice")
    public String nice();
}
