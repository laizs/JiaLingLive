package com.gzzsc.lai.controller;

import com.gzzsc.lai.provider.entity.User;
import com.gzzsc.lai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className UserController
 * @Deacription TODO
 * @Author laizs
 * @Date 2021/1/15 11:32
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/{id}")
    public User getUser(@PathVariable(value = "id") int id){
        return this.userService.getById(id);
    }
}
