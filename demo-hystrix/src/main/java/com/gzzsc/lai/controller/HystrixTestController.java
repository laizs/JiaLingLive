package com.gzzsc.lai.controller;

import com.gzzsc.lai.service.HelloService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

/**
 * @ClassName HystrixTestController
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/7/20 16:55
 **/
@SuppressWarnings("ALL")
@RestController
public class HystrixTestController {
    private final static Logger logger= LoggerFactory.getLogger(HystrixTestController.class);
    @Autowired
    private RestTemplate restTemplate;
    private final static String providerApplicationName="eureka-provider";
    @Autowired
    private HelloService helloService;
    @GetMapping("/test/{username}")
    @HystrixCommand(fallbackMethod = "fallbackMethod",commandProperties = {
            // 降级处理超时时间设置
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "10"),
            //任意时间点允许的最高并发数。超过该设置值后，拒绝执行请求。
            @HystrixProperty(name="fallback.isolation.semaphore.maxConcurrentRequests",value = "1")

    })//定义服务降级的处理方法
    public String test(@PathVariable  String username){
        logger.info("---method--test:{}",username);
        logger.info("---method--test-线程："+Thread.currentThread().getName());
        String url="http://"+providerApplicationName+"/eurekaProvider/hello";
        String s=this.restTemplate.getForObject(url,String.class);
        return s;
    }

    /**
     * 断路器测试
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallbackMethod",commandProperties = {

            // 是否开启断路器，默认true
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            // 请求次数，默认20
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            // 时间窗口期，默认5000
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000"),
             // 失败率到达多少后跳闸，默认50
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10"
            )

    })
    @GetMapping("/circuitBreakerTest/{username}")
    public String circuitBreakerTest(@PathVariable String username){
        logger.info("执行方法----circuitBreakerTest----");
        if(username.equals("a")){
            throw new RuntimeException("========不能为a=======");
        }
       String uuid= UUID.randomUUID().toString();
        return Thread.currentThread().getName()+"调用成功，流水号："+uuid;
    }
    @GetMapping("/common")
    public String common(){
        logger.info("---method--common-线程："+Thread.currentThread().getName());
        return "common";
    }

    /**
     * 服务降级调用的方法
     * @param username
     * @return
     */
    public String fallbackMethod(@PathVariable String username){
        logger.info("--服务降级--fallbackMethod：{}",username);
        return "fallback";
    }
    @GetMapping("/testFeign/hello")
    public String testFeignHello(){
        return  this.helloService.hello();
    }
    @GetMapping("/testFeign/nice")
    public String testFeignNice(){
        return this.helloService.nice();
    }

}
