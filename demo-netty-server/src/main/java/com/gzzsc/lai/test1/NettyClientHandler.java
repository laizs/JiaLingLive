package com.gzzsc.lai.test1;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.EventExecutorGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @className NettyClientHandler
 * @Deacription TODO
 * @Author laizs
 * @Date 2021/8/25 17:52
 **/
public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    public static final Logger logger= LoggerFactory.getLogger(ChannelInboundHandlerAdapter.class);
    //连接建立后，netty会自动调用channelActive方法
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //创建一条消息，发送给服务器
        BaseMessage message=new BaseMessage(new Date(),"你好，服务器！",0);
        ByteBuf byteBuf=MessageUtils.getByteBuf(message);
        ctx.writeAndFlush(byteBuf);
        logger.info("客户端给服务器发送消息:{}", JSON.toJSONString(message));
    }
    //读取服务器消息
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf= (ByteBuf) msg;
        BaseMessage message=MessageUtils.getBaseMessage(byteBuf);
        logger.info("客户端收到服务器消息：{}",JSON.toJSONString(message));
    }
}
