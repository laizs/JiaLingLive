package com.gzzsc.lai.mq.controller.distributed.transaction;

import org.apache.rocketmq.client.producer.LocalTransactionExecuter;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
*@className MyLocalTransactionExecuter
*@Deacription 模拟本地事务
*@Author laizs
*@Date 2022/3/16 16:43
**/
@Service
public class MyLocalTransactionExecuter implements LocalTransactionExecuter {
    private final static Logger LOGGER= LoggerFactory.getLogger(MyLocalTransactionExecuter.class);
    @Override
    public LocalTransactionState executeLocalTransactionBranch(Message message, Object o) {
        LOGGER.info("模拟本地事务成功");
        LOGGER.info("提交事务消息的commit指令");
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}
