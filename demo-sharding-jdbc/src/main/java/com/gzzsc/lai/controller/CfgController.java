package com.gzzsc.lai.controller;

import com.gzzsc.lai.entity.Cfg;
import com.gzzsc.lai.mapper.CfgMapper;
import com.gzzsc.lai.service.CfgService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Cfg save(@RequestBody  Cfg cfg){
            this.cfgService.save(cfg);
            return cfg;
    }


}
