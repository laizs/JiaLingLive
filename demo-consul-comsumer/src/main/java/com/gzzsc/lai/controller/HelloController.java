package com.gzzsc.lai.controller;

import com.gzzsc.lai.model.User;
import com.gzzsc.lai.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * @className HelloControoler
 * @Deacription 测试controller
 * @Author laizs
 * @Date 2021/3/11 13:24
 **/
@RestController
public class HelloController {
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HelloService helloService;
    private Logger logger= LoggerFactory.getLogger(HelloController.class);
    @GetMapping("/hello")
    public String hello(){
        ServiceInstance serviceInstance=loadBalancerClient.choose("consul-provider");
        System.out.println("serviceInstance:"+serviceInstance);
        URI uri=serviceInstance.getUri();
        System.out.println("uri:"+uri);
        String callString=new RestTemplate().getForObject(uri+"/hello",String.class);
        System.out.println("callString:"+callString);
        return callString;
    }
    @GetMapping("/hello1")
    public String hello1(){
        String url="http://consul-provider/hello";
        String callString=restTemplate.getForObject(url,String.class);
        System.out.println("callString:"+callString);
        return callString;
    }
    @GetMapping("/feignHello")
    public User feignHello(){
        User u=new User();
        u.setAge(10);
        u.setName("tiger");
        logger.info("^^^^^^^^^^^^^^^");
        return this.helloService.hello(u);
    }
}
