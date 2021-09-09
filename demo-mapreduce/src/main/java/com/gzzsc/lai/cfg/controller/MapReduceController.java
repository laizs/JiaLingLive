package com.gzzsc.lai.cfg.controller;

import com.gzzsc.lai.mapreduce.MapReduceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @className MapReduceController
 * @Deacription 测试mapreduce
 * @Author laizs
 * @Date 2021/9/3 10:44
 **/
@RestController
public class MapReduceController {
    private final static Logger LOGGER= LoggerFactory.getLogger(MapReduceController.class);
    @Autowired
    private MapReduceClient mapReduceClient;
    @RequestMapping("/mapreduce")
    public String mapreduce(){
        String jobName="mr1"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        try {
            this.mapReduceClient.wordCount(jobName,"/wc_input");
            return "mapreduce 任务处理成功";
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "异常";
    }
}
