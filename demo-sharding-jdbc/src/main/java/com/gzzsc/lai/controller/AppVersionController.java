package com.gzzsc.lai.controller;

import com.gzzsc.lai.provider.entity.AppVersion;
import com.gzzsc.lai.service.AppVersionService;
import com.gzzsc.lai.utils.SnowFlakeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        for(long i=0;i<3;i++){
            AppVersion appVersion=new AppVersion();
            appVersion.setId(SnowFlakeUtils.getId());
            appVersion.setVersion("v"+i);
            this.appVersionService.save(appVersion);
        }
        return "success";
    }
    @ApiOperation("新增单个数据")
    @PostMapping("/appVersion")
    public String saveAll(@RequestBody AppVersion appVersion){
        /*for(int i=0;i<10;i++){
            System.out.println("生成id:"+SnowFlakeUtils.getId());
        }*/

        appVersion.setId(SnowFlakeUtils.getId());
        AppVersion appVersion1=new AppVersion();
        appVersion1.setId(SnowFlakeUtils.getId());
        appVersion1.setVersion(appVersion.getVersion()+"ss");
        this.appVersionService.saveWidthExceprion(appVersion,appVersion1);
        return "success";
    }

}
