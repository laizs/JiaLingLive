package com.gzzsc.lai.test1;

import java.util.Date;

/**
 * @className BaseMessage
 * @Deacription 通用消息格式封装
 * @Author laizs
 * @Date 2021/8/25 16:03
 **/
public class BaseMessage {
    //消息创建时间
    private Date createTime;
    //消息接受时间
    private Date receiveTime;
    //消息内容
    private String messageContent;
    //消息流水号
    private int messageId;

    public BaseMessage(Date createTime, String messageContent, int messageId) {
        this.createTime = createTime;
        this.messageContent = messageContent;
        this.messageId = messageId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }
}
