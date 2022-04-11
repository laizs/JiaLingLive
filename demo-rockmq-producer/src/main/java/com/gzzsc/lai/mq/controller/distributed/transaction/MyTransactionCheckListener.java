package com.gzzsc.lai.mq.controller.distributed.transaction;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionCheckListener;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
*@className MyTransactionCheckListener
*@Deacription 消息状态回测
*@Author laizs
*@Date 2022/3/16 16:31
**/
@Component
public class MyTransactionCheckListener  implements TransactionCheckListener {
    private final static Logger LOGGER= LoggerFactory.getLogger(MyTransactionCheckListener.class);
    @Override
    public LocalTransactionState checkLocalTransactionState(MessageExt messageExt) {
        LOGGER.info("消息状态回测:"+messageExt);
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}
