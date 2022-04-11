package com.gzzsc.lai.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @className RelatedController
 * @Deacription 测试关联级联模式
 * @Author laizs
 * @Date 2021/3/19 10:35
 **/
@RestController
@RequestMapping("/related")
public class RelatedController {
    @RequestMapping("/a")
    public String flowRelateA(){
        String s="a 接口";
        System.out.println(s);
        return s;
    }
    @RequestMapping("/b")
    public String flowRelateB(){
        String s="b 接口";
        System.out.println(s);
        return s;
    }
}
