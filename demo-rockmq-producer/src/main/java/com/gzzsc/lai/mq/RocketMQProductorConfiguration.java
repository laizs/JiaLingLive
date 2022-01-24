package com.gzzsc.lai.mq;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * mq生产者bean配置
 * @author laizs
 * @time 2018年1月4日下午3:37:14
 */
@SpringBootConfiguration
public class RocketMQProductorConfiguration {
	public static final Logger loggger = LoggerFactory.getLogger(RocketMQProductorConfiguration.class);
	/**
	 * rocketMq名称服务器地址，以分号分割
	 */
    @Value("${rocketmq.consumer.namesrvAddr}")
    private String namesrvAddr;
    /**
     * rocketMq生产者组名称
     */
    @Value("${rocketMq.producerGroup}")
    private String producerGroup;
    /**
     * 消息mq生产者bean
     * @return
     * @throws Exception
     */
    @Bean(name="mqProducer",initMethod="start",destroyMethod="shutdown")
    public DefaultMQProducer mqProducer() throws Exception {
    	DefaultMQProducer producer=new DefaultMQProducer();
    	producer.setNamesrvAddr(namesrvAddr);//rocketMq名称服务器地址，以分号分割 
    	producer.setProducerGroup(producerGroup);//生产者组
    	producer.setVipChannelEnabled(false);//
		producer.setDefaultTopicQueueNums(16);//队列数
    	loggger.info("###初始化mqMQProducer, namesrvAddr:{}, producerGroup:{}",namesrvAddr,producerGroup);
    	return producer;
    }
}
