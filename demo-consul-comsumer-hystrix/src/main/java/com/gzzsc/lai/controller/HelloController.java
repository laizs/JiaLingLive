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
import java.util.UUID;

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
    /**
     * 断路器测试
     * @param username
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallbackMethod",commandProperties = {

            // 是否开启断路器，默认true
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            // 一个rolling window内最小的请 求数。如果设为20，那么当一个rolling window的时间内（比如说1个rolling window是10秒）收到19个请
            //求， 即使19个请求都失败，也不会触发circuit break。默认20
            //在熔断开关闭合情况下，在进行失败率判断之前，一个采样周期内必须进行至少N个请求才能进行采样统计，目的是有足够的采样使得失败率计算正确，默认为20。
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            // 时间窗口期，默认5000
            //熔断后的重试时间窗口，且在该时间窗口内只允许一次重试。即在熔断开关打开后，在该时间窗口允许有一次重试，如果重试成功，
            // 则将重置Health采样统计并闭合熔断开关实现快速恢复，否则熔断开关还是打开状态，执行快速失败。
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            //在一个rolling windows的时间内，断路器打开错误百分比条件，失败率到达多少后跳闸，默认50
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

    /**
     * 断路器测试
     * @param username
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallbackMethod",
            threadPoolKey = "myThreadPool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "3")
            },
            commandProperties = {

            // 是否开启断路器，默认true
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            // 一个rolling window内最小的请 求数。如果设为20，那么当一个rolling window的时间内（比如说1个rolling window是10秒）收到19个请
            //求， 即使19个请求都失败，也不会触发circuit break。默认20
            //在熔断开关闭合情况下，在进行失败率判断之前，一个采样周期内必须进行至少N个请求才能进行采样统计，目的是有足够的采样使得失败率计算正确，默认为20。
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            // 时间窗口期，默认5000
            //熔断后的重试时间窗口，且在该时间窗口内只允许一次重试。即在熔断开关打开后，在该时间窗口允许有一次重试，如果重试成功，
            // 则将重置Health采样统计并闭合熔断开关实现快速恢复，否则熔断开关还是打开状态，执行快速失败。
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            //在一个rolling windows的时间内，断路器打开错误百分比条件，失败率到达多少后跳闸，默认50
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10"
            )

    })
    @GetMapping("/circuitBreakerTest1/{username}")
    public String circuitBreakerTest1(@PathVariable String username){
        logger.info("执行方法----circuitBreakerTest1----");
        if(username.equals("a")){
            throw new RuntimeException("========不能为a=======");
        }
        String uuid= UUID.randomUUID().toString();
        return Thread.currentThread().getName()+"调用成功，流水号："+uuid;
    }


    /**
     * 断路器测试
     * @param username
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallbackMethod",
            commandProperties = {

                    // 是否开启断路器，默认true
                    @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
                    // 一个rolling window内最小的请 求数。如果设为20，那么当一个rolling window的时间内（比如说1个rolling window是10秒）收到19个请
                    //求， 即使19个请求都失败，也不会触发circuit break。默认20
                    //在熔断开关闭合情况下，在进行失败率判断之前，一个采样周期内必须进行至少N个请求才能进行采样统计，目的是有足够的采样使得失败率计算正确，默认为20。
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
                    // 时间窗口期，默认5000
                    //熔断后的重试时间窗口，且在该时间窗口内只允许一次重试。即在熔断开关打开后，在该时间窗口允许有一次重试，如果重试成功，
                    // 则将重置Health采样统计并闭合熔断开关实现快速恢复，否则熔断开关还是打开状态，执行快速失败。
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
                    //在一个rolling windows的时间内，断路器打开错误百分比条件，失败率到达多少后跳闸，默认50
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10"
                    )

            })
    @GetMapping("/circuitBreakerTest2/{username}")
    public String circuitBreakerTest2(@PathVariable String username){
        logger.info("执行方法----circuitBreakerTest2----");
        if(username.equals("a")){
            throw new RuntimeException("========不能为a=======");
        }
        String uuid= UUID.randomUUID().toString();
        return Thread.currentThread().getName()+"调用成功，流水号："+uuid;
    }

    /**
     * 断路器测试
     * @param username
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallbackMethod",groupKey = "circuitBreakerTest3",commandKey = "circuitBreakerTest3333",
            commandProperties = {
                    // 是否开启断路器，默认true
                    @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
                    // 一个rolling window内最小的请 求数。如果设为20，那么当一个rolling window的时间内（比如说1个rolling window是10秒）收到19个请
                    //求， 即使19个请求都失败，也不会触发circuit break。默认20
                    //在熔断开关闭合情况下，在进行失败率判断之前，一个采样周期内必须进行至少N个请求才能进行采样统计，目的是有足够的采样使得失败率计算正确，默认为20。
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
                    // 时间窗口期，默认5000
                    //熔断后的重试时间窗口，且在该时间窗口内只允许一次重试。即在熔断开关打开后，在该时间窗口允许有一次重试，如果重试成功，
                    // 则将重置Health采样统计并闭合熔断开关实现快速恢复，否则熔断开关还是打开状态，执行快速失败。
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
                    //在一个rolling windows的时间内，断路器打开错误百分比条件，失败率到达多少后跳闸，默认50
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10"
                    )

            })
    @GetMapping("/circuitBreakerTest3/{username}")
    public String circuitBreakerTest3(@PathVariable String username){
        logger.info("执行方法----circuitBreakerTest3----");
        if(username.equals("a")){
            throw new RuntimeException("========不能为a=======");
        }
        String uuid= UUID.randomUUID().toString();
        return Thread.currentThread().getName()+"调用成功，流水号："+uuid;
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

}
