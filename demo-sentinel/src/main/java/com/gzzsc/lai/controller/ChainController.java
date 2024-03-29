package com.gzzsc.lai.controller;

import com.gzzsc.lai.controller.service.ChainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className ChainController
 * @Deacription 测试链路限流
 * @Author laizs
 * @Date 2021/3/19 10:55
 **/
@RestController
@RequestMapping("/chain")
public class ChainController {
    @Autowired
    private ChainService chainService;
    @GetMapping("a")
    public String flowRelatedA(){
        chainService.chainTest();
        System.out.println("接口a");
        return "接口a";
    }
    @GetMapping("b")
    public String flowRelatedB(){
        chainService.chainTest();
        System.out.println("接口b");
        return "接口b";
    }
}
