package com.gzzsc.lai.mq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class TopicStarter implements ApplicationRunner {
    protected static final Logger logger = LoggerFactory.getLogger(TopicStarter.class);

    /**
     * rocketMq名称服务器地址，以分号分割
     */
    @Value("${rocketmq.consumer.namesrvAddr}")
    private String namesrvAddr;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        {
            logger.info("#############创建topic##################");
           /* DefaultMQProducer producer = new DefaultMQProducer("9527");
            producer.setNamesrvAddr(namesrvAddr);
            producer.start();
            producer.setCreateTopicKey("9527");
            producer.createTopic("9527","9527",16,1);
            producer.shutdown();*/
        }
    }
}
