package com.gzzsc.lai.controller;

import com.gzzsc.lai.entity.AppVersion;
import com.gzzsc.lai.service.AppVersionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName AppVersionController
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/6/24 11:16
 **/
@RestController
@RequestMapping("/appVersion")
@Api(tags = "版本信息接口")
public class AppVersionController {
    @Autowired
    private AppVersionService appVersionService;
    @GetMapping("/")
    @ApiOperation("获取所有版本列表")
    public List<AppVersion> findAll(){
        return this.appVersionService.findAll();
    }
    @ApiOperation("批量新增数据")
    @PostMapping("/")
    public String saveAll(){
        for(long i=0;i<=10;i++){
            AppVersion appVersion=new AppVersion();
            appVersion.setVersion("v"+i);
            this.appVersionService.save(appVersion);
        }
        return "success";
    }
}
