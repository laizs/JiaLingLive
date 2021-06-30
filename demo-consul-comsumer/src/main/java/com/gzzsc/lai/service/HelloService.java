package com.gzzsc.lai.service;

import com.gzzsc.lai.cfg.FeignConfiguration;
import com.gzzsc.lai.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @className HelloService
 * @Deacription feign接口
 * @Author laizs
 * @Date 2021/3/11 17:11
 **/
@FeignClient(contextId = "feignClient",name = "consul-provider",configuration = FeignConfiguration.class)
public interface HelloService {
    @PostMapping("/hello")
    @ResponseBody
    User hello(@RequestBody  User u);
}
