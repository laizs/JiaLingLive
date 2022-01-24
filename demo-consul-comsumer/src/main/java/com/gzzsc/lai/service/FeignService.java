package com.gzzsc.lai.service;

import com.gzzsc.lai.cfg.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @className FeignService
 * @Deacription TODO
 * @Author laizs
 * @Date 2022/1/20 17:37
 **/
@FeignClient(contextId = "feignClient1",name = "consul-provider",configuration = FeignConfiguration.class)
public interface FeignService {
    @RequestMapping("/sleep/{sec}")
    @ResponseBody
    public String sleep(@PathVariable int sec);
}