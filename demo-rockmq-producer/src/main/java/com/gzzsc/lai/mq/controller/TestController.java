package com.gzzsc.lai.mq.controller;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.gzzsc.lai.mq.controller.distributed.transaction.*;

/**
 * @className TestController
 * @Author laizs
 * @Date 2022/1/10 11:07
 **/
@SuppressWarnings("ALL")
@RestController
public class TestController {
    private final static Logger logger = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private DefaultMQProducer mqProducer;

    @Autowired
    private TransactionProducer transactionProducer;
    @RequestMapping("/test1/{orderId}")
    public void test1(@PathVariable Integer orderId) {
        for (int i = 0; i < 10; i++) {

            Map mqMsgMap = new HashMap();
            mqMsgMap.put("opt", "report" + i);
            mqMsgMap.put("orderId", orderId);
            try {
                String msgJson2 = JSON.toJSONString(mqMsgMap);                 //将logMessage转化为json
                Message msg2 = new Message("myTopic",                         //topic 主题
                        "*",                                                 //tag 标签
                        msgJson2.getBytes("utf-8")                        //body 消息的实体部分
                );
                SendResult sendResult = mqProducer.send(msg2, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                        logger.info("MessageQueue list:" + list.size());
                        for (MessageQueue q : list) {
                            logger.info("MessageQueue :" + q);
                        }

                        Integer id = (Integer) o;
                        int index = id % list.size();//队列选择算法
                        return list.get(index);
                    }
                }, orderId);

                logger.info("baiduRunnermq发送消息msg,topic:" + msg2.getTopic() + ",tag:" + msg2.getTags() + ",body:" + new String(msg2.getBody()));
                logger.info("sendResult:{}", sendResult);
            } catch (Exception e) {
                logger.warn("异常:", e);
            }
        }

    }

    @RequestMapping("/test2/{userId}")
    public void test2(@PathVariable Integer userId) {

        Map mqMsgMap = new HashMap();
        mqMsgMap.put("userId", userId);
        try {
            String msgJson2 = JSON.toJSONString(mqMsgMap);                 //将logMessage转化为json
            Message msg2 = new Message("9527",                         //topic 主题
                    "*",                                                 //tag 标签
                    msgJson2.getBytes("utf-8")                        //body 消息的实体部分
            );
            msg2.setWaitStoreMsgOK(true);
            SendResult sendResult = mqProducer.send(msg2);
            logger.info("baiduRunnermq发送消息msg,topic:" + msg2.getTopic() + ",tag:" + msg2.getTags() + ",body:" + new String(msg2.getBody()));
            logger.info("sendResult:{}", sendResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/sendTransationMsg/{msg}")
    public void sendTransationMsg(@PathVariable String msg) {
    }
}
