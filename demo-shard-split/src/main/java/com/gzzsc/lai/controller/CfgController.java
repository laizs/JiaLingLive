package com.gzzsc.lai.controller;

import com.gzzsc.lai.provider.entity.MyCfg;
import com.gzzsc.lai.service.CfgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName CfgController
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/6/22 17:53
 **/
@SuppressWarnings("ALL")
@RestController
@Api(tags = "配置信息接口")
@RequestMapping("/cfg")
public class CfgController {
    @Autowired
    private CfgService cfgService;
    @PostMapping("/")
    @ApiOperation("新增数据")
    public MyCfg save(@RequestBody  MyCfg cfg){
        this.cfgService.save(cfg);
        return cfg;
    }
    @PostMapping("/saveAll")
    @ApiOperation("批量新增10调数据")
    public String saveAll(){
        for(long i=1;i<=10;i++){
            MyCfg cfg=new MyCfg();
            cfg.setName("scott"+i);
            cfg.setValue("tiger"+i);
            //cfg.setId(i);
            this.cfgService.save(cfg);
        }
        return "success";
    }
    @DeleteMapping("/")
    @ApiOperation("删除所有数据")
    public int deleteAll(){
        return this.cfgService.deleteAll();
    }

    @GetMapping("/")
    @ApiOperation("查询所有")
    public List<MyCfg> list(){
        return this.cfgService.getAll();
    }
    @GetMapping("/{id}")
    @ApiOperation("根据id查询")
    public  MyCfg getById(@PathVariable(name = "id") Long id){
        return this.cfgService.getById(id);
    }


}
