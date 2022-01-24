package com.gzzsc.lai.mq;


import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RocketMQ消费者<br>
 * 作用：主要用来消费客服端发送过来日志信息
 * @author wangsc
 * @date 2017年9月29日上午11:21:06
 */
public class Consumer {
	/**
	 * 日志对象
	 */
	static final Logger log=LoggerFactory.getLogger(Consumer.class);
	/**
	 * RocketMQ消费者（push）
	 */
	private DefaultMQPushConsumer consumer;
	/**
	 * 消费者集群的名称
	 */
	private String consumerGroup;
	/**
	 * 消费者所连接的NameService地址
	 */
	private String namesrvAddr;
	/**
	 * 消费的消息标题
	 */
	private String tag;
	/**
	 * 消费的消息主题
	 */
	private String topic;
	/**
	 * rocketmq消费者消费线程最小值
	 */
    private int consumeThreadMin=1;
	/**
	 * rocketmq消费者消费线程最大值，默认是20
	 */
    private int consumeThreadMax=1;
	/**
	 * 消息监听器
	 */
	private MessageListener messageListener;
	/**
	 * 构造方法
	 * @param consumerGroup   消费者集群的名称
	 * @param namesrvAddr     消费者所连接的NameService地址
	 * @param tag             消费的消息标题
	 * @param topic           消费的消息主题
	 * @param messageListener 消息监听器
	 */
	public Consumer(String consumerGroup, String namesrvAddr, String tag, String
			topic , MessageListener messageListener, int consumeThreadMin, int consumeThreadMax){
			this.consumerGroup=consumerGroup;
			this.namesrvAddr=namesrvAddr;
			this.tag=tag;
			this.topic=topic;
			this.messageListener=messageListener;
			this.consumeThreadMin=consumeThreadMin;
			this.consumeThreadMax=consumeThreadMax;
	}
	
	public void init(){
		log.info("start init "+consumerGroup);
		System.out.println("consumeThreadMin:"+consumeThreadMin+",consumeThreadMax:"+consumeThreadMax);
		consumer = new DefaultMQPushConsumer(consumerGroup);                        //设置消费者集群的名称
		consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);   //从队列头部开始消费
		log.info(namesrvAddr);
		consumer.setNamesrvAddr(namesrvAddr);                                       //设置消费者所连接的NameService地址
		consumer.setVipChannelEnabled(false);
		consumer.setConsumeThreadMin(consumeThreadMin);
		consumer.setConsumeThreadMax(consumeThreadMax);
		try {
			consumer.subscribe(topic, tag);                                         //设置消费的消息主题及标题
			consumer.registerMessageListener(messageListener);                      //设置消息监听器
			log.info(consumerGroup+" init success.");
		} catch (MQClientException e) {
			log.info(consumerGroup+" init faild.");
		}
	}
	
	public void start(){
		log.info("mq consumer({}) 启动...  ",consumerGroup);
		try {
			consumer.start();                                                        //开启消费者
			log.info("mq consumer({}) 启动成功！  ",consumerGroup);
		} catch (MQClientException e) {
			e.printStackTrace();
			log.info("mq consumer({}) 启动失败！  ",consumerGroup);
		}


	}
	public void destroy(){
		consumer.shutdown();                                                         //关闭消费者
	}
}
