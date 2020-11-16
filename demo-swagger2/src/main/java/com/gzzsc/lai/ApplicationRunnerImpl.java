package com.gzzsc.lai;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName ApplicationRunnerImpl
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/11/12 16:47
 **/
@Component
public class ApplicationRunnerImpl implements ApplicationRunner {
    private final static Logger LOGGER= LoggerFactory.getLogger(ApplicationRunnerImpl.class);
    @Override
    public void run(ApplicationArguments args) throws Exception {
        final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String time=sdf.format(new Date());
                    long orderNo=System.currentTimeMillis();
                    LOGGER.info("时间:{},订单:{}支付完成",time,orderNo);
                }

            }
        }).start();
    }
}
