package com.gzzsc.lai.controller;

import com.alibaba.fastjson.JSON;
import com.gzzsc.lai.annotation.ApiReqParam;
import com.gzzsc.lai.annotation.CheckTokenAndTimeStamp;
import com.gzzsc.lai.annotation.Log;
import com.gzzsc.lai.api.ApiReq;
import com.gzzsc.lai.api.ApiRsp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TestController
 * @Deacription 测试
 * @Author laizs
 * @Date 2020/5/6 10:19
 **/
@RestController
public class TestController {
    private final static Logger logger= LoggerFactory.getLogger(TestController.class);
    @GetMapping("/test")
    @Log
    public boolean test(){
        logger.info("这里是牛逼的业务执行...");
        return true;
    }
    @PostMapping("/test")
    @Log
    @CheckTokenAndTimeStamp
    public ApiRsp test(@RequestBody @ApiReqParam ApiReq req){
        logger.info("请求数据："+ JSON.toJSONString(req));
        logger.info("这里是牛逼的业务执行...");
        ApiRsp<Map> apiRsp=new ApiRsp<>();
        Map body=new HashMap();
        body.put("id",12345678);
        apiRsp.setBody(body);
        return apiRsp;
    }
}
