package com.gzzsc.lai.dubbo.impl;

import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName HelloServiceImpl
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/5/19 14:57
 **/
@Service//@Service是dubbo的service  不是spring的service
public class HelloServiceImpl implements HelloService {
    private  final  static Logger LOGGER= LoggerFactory.getLogger(HelloServiceImpl.class);
    @Override
    public String hello(String name) {
        LOGGER.info("执行方法-->hello");
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello:"+name;
    }
}
