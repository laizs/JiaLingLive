package com.gzzsc.lai.controller;

import com.gzzsc.lai.service.UnionInfoSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className MybatisCacheController
 * @Deacription mybatis 一级缓存/二级缓存测试
 * @Author laizs
 * @Date 2021/8/23 17:47
 **/
@RestController
public class MybatisCacheController {
    @Autowired
    private UnionInfoSevice unionInfoSevice;
    @RequestMapping("/oneLevelCache")
    public String oneLevelCache(){
        unionInfoSevice.doTestOneCached();
        return "sf";
    }
    @RequestMapping("/secondLevelCache")
    public String secondLevelCache(){
        unionInfoSevice.doTestSecondCached();
        return "sf";
    }
    @RequestMapping("/update/{id}")
    public String update(@PathVariable  String id){
        this.unionInfoSevice.update(id);
        return "updated";
    }
}
