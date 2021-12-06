package com.gzzsc.lai.controller;

import com.gzzsc.lai.provider.entity.Advertisement;
import com.gzzsc.lai.service.AdvertisementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AdvertisementComtrollertiger
 *
 * tiger
 *
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/9/2 14:03
 **/
@RestController
@Api(tags = "广告")
@RequestMapping("/ad")
public class AdvertisementComtroller {
    private static final Logger logger= LoggerFactory.getLogger(AdvertisementComtroller.class);
    @Autowired
    private AdvertisementService advertisementService;
    @GetMapping("/{id}")
    @ApiOperation("获取广告")
    public Advertisement getById(@PathVariable  String id){
        return this.advertisementService.getById(id);
    }
}
