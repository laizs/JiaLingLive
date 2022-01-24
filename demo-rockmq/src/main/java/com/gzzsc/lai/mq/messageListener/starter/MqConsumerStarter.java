package com.gzzsc.lai.mq.messageListener.starter;

import com.gzzsc.lai.mq.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * spring初始化结束后,执行
 * @author laizs
 * @time 2017年9月27日下午3:38:27
 *
 */
@Component
public class MqConsumerStarter implements ApplicationRunner {
    protected static final Logger logger = LoggerFactory.getLogger(MqConsumerStarter.class);
    /**
     * 消息消费者
     */
    @Resource(name="myComsumer")
    private Consumer myComsumer;
    @Resource(name="myComsumer2")
    private Consumer myComsumer2;



    @Override
    public void run(ApplicationArguments args) throws Exception {
        {
            logger.info("#############spring加载完执行##################");
            myComsumer.start();
            myComsumer2.start();
        }
    }
}
