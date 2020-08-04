package com.gzzsc.lai.provider.customer.controller;

import com.alibaba.fastjson.JSON;
import com.gzzsc.lai.customer.entity.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @ClassName CustomerController
 * @Deacription 模拟消费者controller
 * @Author laizs
 * @Date 2020/7/17 15:38
 **/
@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final  static Logger log= LoggerFactory.getLogger(CustomerController.class);
    @Resource(name = "poolingRestTemplate")
    private RestTemplate restTemplate;
    @GetMapping("/user")
    public List<UserInfo> listUsers(){
        String uri="http://127.0.0.1/provider/user";
        /*List<UserInfo> list=this.restTemplate.getForObject(uri,List.class);*/
        ResponseEntity<List> responseEntity =this.restTemplate.getForEntity(uri,List.class);
        List<UserInfo> list=responseEntity.getBody();
        log.info("StatusCode:{}", JSON.toJSONString(responseEntity.getStatusCode()));
        log.info("StatusCodeValue:{}",responseEntity.getStatusCodeValue());
        log.info("Headers:{}",responseEntity.getHeaders());
        return list;
    }
    @GetMapping("/user/{id}")
    public UserInfo getById(@PathVariable String id){
        String uri="http://127.0.0.1/provider/user/"+id;
        UserInfo u=this.restTemplate.getForObject(uri,UserInfo.class);
        return u;
    }
    @PostMapping("/user")
    public UserInfo addUser(@RequestBody  UserInfo userInfo){
        String uri="http://127.0.0.1/provider/user";
       /* UserInfo user= this.restTemplate.postForObject(uri,userInfo,UserInfo.class);*/
        UserInfo user=null;
        try {
            URI uri1=new URI(uri);
            RequestEntity<UserInfo> requestEntity=RequestEntity.post(uri1).body(userInfo);
            ResponseEntity<UserInfo> responseEntity=this.restTemplate.exchange(requestEntity, UserInfo.class);
            user=responseEntity.getBody();
            log.info("StatusCode:{}", JSON.toJSONString(responseEntity.getStatusCode()));
            log.info("StatusCodeValue:{}",responseEntity.getStatusCodeValue());
            log.info("Headers:{}",responseEntity.getHeaders());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

}
