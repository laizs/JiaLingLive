package com.gzzsc.lai.controller;

import com.gzzsc.lai.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @ClassName ClienterController
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/7/15 15:56
 **/
@RestController
@Slf4j
public class ClienterController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HelloService helloService;
    @Autowired
    private DiscoveryClient discoveryClient;
    private final static String providerApplicationName="eureka-provider";
    @GetMapping("/feignRequest")
    public String feignRequest(){
        String s=helloService.nice();
        return s;
    }
    @GetMapping("/commonRequest")
    public String commonRequest(){
        List<String> services=discoveryClient.getServices();
        for(String s:services){
            log.info("commonRequest service:{}",s);
        }
        String url="http://"+providerApplicationName+"/eurekaProvider/hello";
        String s=this.restTemplate.getForObject(url,String.class);
        System.out.println("----------------");
        System.out.println("aaaaaaaaaaaaaaa");
        return s;
    }
}
