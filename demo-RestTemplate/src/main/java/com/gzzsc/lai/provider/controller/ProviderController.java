package com.gzzsc.lai.provider.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.gzzsc.lai.provider.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName ProviderController
 * @Deacription 模拟
 * @Author laizs
 * @Date 2020/7/17 14:57
 **/
@RestController
@RequestMapping("/provider")
@Slf4j
public class ProviderController {
    @Autowired
    private RestTemplate restTemplate;
    static List<User> users=new ArrayList<>();
    static {
        User user1=new User("1","LiLei","1380000001",0,20,66.5);
        User user2=new User("2","HanMeiMei","1390000001",1,19,45);
        User user3=new User("3","AiLance","1370000001",1,35,81.9);
        users.add(user1);
        users.add(user2);
        users.add(user3);
    }
    /**
     * 获取所有用户
     * @return
     */
    @GetMapping("/user")
    public List<User> listUsers(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * 查询单个用户
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    public User getById(@PathVariable String id){
        List<User> list=users.stream().filter(u ->u.getId().equals(id)).collect(Collectors.toList());
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @PostMapping(value="/user",produces = {"text/plain","application/json"})
    public User addUser(@RequestBody User user){
        log.info("添加用户：{}", JSON.toJSONString(user));
        return user;
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/user/{id}")
    public int delUser(@PathVariable String id){
        List<User> list=users.stream().filter(u ->u.getId().equals(id)).collect(Collectors.toList());
        if(list.size()>0){
            log.info("删除用户：{}",JSON.toJSONString(list.get(0)));
            return 1;
        }
        return 0;
    }

}
