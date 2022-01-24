package com.gzzsc.lai.mq.messageListener;

import lombok.SneakyThrows;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

@Component("myConsumerListener")
public class MyConsumerListener implements MessageListenerOrderly {


	static final Logger logger= LoggerFactory.getLogger(MyConsumerListener.class);
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/*@SneakyThrows
	@Override
	*//**
	 * 消费消息调用的方法
	 * @return ConsumeConcurrentlyStatus 消息是否成功发送
	 *//*
	public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
		
		for (MessageExt message:msgs){


			try {
				//模拟消息处理时长消耗不一
				long l=Long.valueOf(new Random().nextInt(1000));
				System.out.println(l);
				Thread.sleep(l);
				logger.info("消费消息：{}",message);
				logger.info("消费消息StoreHost：{},QueueId:{}",message.getStoreHost(),message.getQueueId());
				logger.info("内容："+new String(message.getBody(),"utf-8"));
			}catch (Exception e){

			}

		}
		return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
	}*/


	@Override
	public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
		for (MessageExt message:list){


			try {
				//模拟消息处理时长消耗不一
				long l=Long.valueOf(new Random().nextInt(1000));
				System.out.println(l);
				Thread.sleep(l);
				logger.info("消费消息：{}",message);
				logger.info("消费消息StoreHost：{},QueueId:{}",message.getStoreHost(),message.getQueueId());
				logger.info("内容："+new String(message.getBody(),"utf-8"));
			}catch (Exception e){

			}

		}
		return ConsumeOrderlyStatus.SUCCESS;
	}
}
