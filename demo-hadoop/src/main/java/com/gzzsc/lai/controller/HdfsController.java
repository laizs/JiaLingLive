package com.gzzsc.lai.controller;

import com.gzzsc.lai.service.HadoopTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className HdfsController
 * @Author laizs
 * @Date 2021/9/1 17:44
 **/
@RestController
public class HdfsController {
    @Autowired
    private HadoopTemplate hadoopTemplate;
    @RequestMapping("/uploadFile")
    public String uploadFile(){
        this.hadoopTemplate.uploadFile("E:\\dev\\winutils-master\\hadoop-3.2.1\\bin\\winutils.exe");
        return "success";
    }
}
