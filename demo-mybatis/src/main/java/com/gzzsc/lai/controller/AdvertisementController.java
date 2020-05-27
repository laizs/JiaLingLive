package com.gzzsc.lai.controller;

import com.gzzsc.lai.entity.Advertisement;
import com.gzzsc.lai.mapper.AdvertisementMapper;
import com.gzzsc.lai.service.AdvertisementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName AdvertisementController
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/5/26 13:42
 **/
@SuppressWarnings("ALL")
@RestController
@RequestMapping("/advertisement")
public class AdvertisementController {
    private final Logger logger= LoggerFactory.getLogger(AdvertisementController.class);
    @Autowired
    private AdvertisementMapper advertisementMapper=null;
    @Autowired
    private AdvertisementService advertisementService;
    @GetMapping("/{id}")
    public Advertisement getById(@PathVariable  String id){
        logger.info("id:"+id);
        Advertisement a=advertisementMapper.selectByPrimaryKey(id);
        return a;
    }
    @DeleteMapping("/{id}")
    public int deleteById(@PathVariable String id){
        return this.advertisementService.deleteById(id);
    }
}
