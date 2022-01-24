package com.gzzsc.lai.mq.messageListener;

import lombok.SneakyThrows;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

@Component("myConsumerListener2")
public class MyConsumerListener2 implements MessageListenerConcurrently{
	static final Logger logger= LoggerFactory.getLogger(MyConsumerListener2.class);
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@SneakyThrows
	@Override
	/**
	 * 消费消息调用的方法
	 * @param msgs 从RocketMQ中获取的消息列表
	 * @param context 
	 * @return ConsumeConcurrentlyStatus 消息是否成功发送
	 */
	public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
		
		for (MessageExt message:msgs){


			logger.info("myConsumerListener2-内容："+new String(message.getBody(),"utf-8"));

		}
		return ConsumeConcurrentlyStatus.RECONSUME_LATER;
	}


}
