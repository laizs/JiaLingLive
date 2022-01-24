package com.gzzsc.lai.mq;
/**
 * xzz2019-08-12 	新增合作联盟广告播放日志上报接口MQ
 */

import com.gzzsc.lai.mq.messageListener.MyConsumerListener;
import com.gzzsc.lai.mq.messageListener.MyConsumerListener2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * mq消费者bean配置
 * @author laizs
 * @time 2017年11月7日上午10:08:01
 */
@SpringBootConfiguration
public class RocketMQConsumerConfiguration {
	public static final Logger loggger = LoggerFactory.getLogger(RocketMQConsumerConfiguration.class);
    @Value("${rocketmq.consumer.namesrvAddr}")
    private String namesrvAddr;
    @Value("${rocketmq.consumer.consumeThreadMin}")
    private int consumeThreadMin;
    @Value("${rocketmq.consumer.consumeThreadMax}")
    private int consumeThreadMax;
    /**
     * 消费者consumerGroup
     */
    @Value("${rocketmq.consumer.consumerGroup}")
    private String consumerGroup;
    /**
     * 消息监听器处理类
     */
    @Resource(name="myConsumerListener")
    private MyConsumerListener myConsumerListener;
    @Resource(name="myConsumerListener2")
    private MyConsumerListener2 myConsumerListener2;
    /**
     *消费者bean
     * @return
     * @throws Exception
     */
    @Bean(name="myComsumer",initMethod="init",destroyMethod="destroy")
    public Consumer myComsumer() throws Exception {
    	Consumer consumer=new Consumer(consumerGroup, namesrvAddr,
    			"*", "myTopic", myConsumerListener,consumeThreadMin,consumeThreadMax);
        return consumer;
    }
    /**
     *消费者bean
     * @return
     * @throws Exception
     */
    @Bean(name="myComsumer2",initMethod="init",destroyMethod="destroy")
    public Consumer myComsumer2() throws Exception {
        Consumer consumer=new Consumer("9527", namesrvAddr,
                "*", "9527", myConsumerListener2,consumeThreadMin,consumeThreadMax);
        return consumer;
    }

    
}
