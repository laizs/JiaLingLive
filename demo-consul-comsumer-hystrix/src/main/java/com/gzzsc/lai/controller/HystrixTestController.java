package com.gzzsc.lai.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * @className HystrixTestController
 * @Deacription Hystrix测试
 * @Author laizs
 * @Date 2021/3/15 13:29
 **/
@RestController
public class HystrixTestController {
    private String serviceName="consul-provider";
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    private static final Logger LOGGER= LoggerFactory.getLogger(HystrixTestController.class);
    /**
     * 断路器测试
     * @param sec
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallbackMethod",commandProperties = {

            // 是否开启断路器，默认true
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            // 一个rolling window内最小的请 求数。如果设为20，那么当一个rolling window的时间内（比如说1个rolling window是10秒）收到19个请
            //求， 即使19个请求都失败，也不会触发circuit break。默认20
            //在熔断开关闭合情况下，在进行失败率判断之前，一个采样周期内必须进行至少N个请求才能进行采样统计，目的是有足够的采样使得失败率计算正确，默认为20。
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
            // 时间窗口期，默认5000
            //熔断后的重试时间窗口，且在该时间窗口内只允许一次重试。即在熔断开关打开后，在该时间窗口允许有一次重试，如果重试成功，
            // 则将重置Health采样统计并闭合熔断开关实现快速恢复，否则熔断开关还是打开状态，执行快速失败。
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
            //在一个rolling windows的时间内，断路器打开错误百分比条件，失败率到达多少后跳闸，默认50
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10"),
            //命令执行超时时间，默认1000ms,这里配置1500毫秒
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500"),
            //执行是否启用超时，默认启用true
            @HystrixProperty(name = "execution.timeout.enabled",value = "true"),
            //发生超时是是否中断，默认true
            @HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout",value = "true")


    })
    @GetMapping("/hystrix/test/{sec}")
    public String hystrixTest(@PathVariable int sec){
        ServiceInstance serviceInstance=loadBalancerClient.choose("consul-provider");
        String callString=restTemplate.getForObject("http://consul-provider/sleep/"+sec,String.class);
        System.out.println("callString:"+callString);
        return callString;
    }
    /**
     * 服务降级调用的方法
     * @param sec
     * @return
     */
    public String fallbackMethod(@PathVariable int sec){
        LOGGER.info("--服务降级--fallbackMethod--sec：{}",sec);
        return "fallback";
    }
}
