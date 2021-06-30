package com.gzzsc.lai.controller;

import com.gzzsc.lai.controller.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @className HelloController
 * @Deacription TODO
 * @Author laizs
 * @Date 2021/3/11 11:38
 **/
@RestController
public class HelloController {
    /**
     * 服务者名称
     */
    @Value("${provider.name}")
    private String name;
    /**
     * 服务端口
     */
    @Value("${server.port}")
    private String port;
    @PostMapping("/hello")
    private User hello(@RequestBody  User user){
        String str="provider:"+name+" port:"+port;
        user.setName(str+":"+user.getName());
        return user;
    }
}
