package com.gzzsc.lai.mq.controller.distributed.transaction;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
*@className TransactionProducer
*@Deacription 分布式消息produser
*@Author laizs
*@Date 2022/3/16 16:22
**/
@SuppressWarnings("ALL")
@Component
public class TransactionProducer {
    private static final Logger logger= LoggerFactory.getLogger(TransactionProducer.class);
    private String producerGroup = "order_trans_group";
    private TransactionMQProducer producer;
    @Value("${rocketmq.consumer.namesrvAddr}")
    private String namesrvAddr;
    @Autowired
    private MyTransactionCheckListener myTransactionCheckListener;
    @Autowired
    private MyLocalTransactionExecuter myLocalTransactionExecuter;
    @PostConstruct
    public void init(){
        producer = new TransactionMQProducer(producerGroup);
        producer.setNamesrvAddr(namesrvAddr);
        producer.setSendMsgTimeout(Integer.MAX_VALUE);
        producer.setTransactionCheckListener(myTransactionCheckListener);
        producer.setVipChannelEnabled(false);
        this.start();
    }
    private void start(){
        try {
            this.producer.start();
            logger.info("启动TransactionProducer---");
        } catch (MQClientException e) {
            logger.info("启动TransactionProducer失败：{}",e);
            e.printStackTrace();
        }
    }
    //事务消息发送
    public TransactionSendResult send(String data, String topic) throws MQClientException {
        Message message = new Message(topic,data.getBytes());
        return this.producer.sendMessageInTransaction(message,myLocalTransactionExecuter,null);
    }


}
