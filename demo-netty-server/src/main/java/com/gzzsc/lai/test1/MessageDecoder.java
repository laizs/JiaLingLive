package com.gzzsc.lai.test1;
import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @className MessageDecoder
 * @Deacription 消息解码器
 * @Author laizs
 * @Date 2021/8/25 16:31
 **/
public class MessageDecoder extends ChannelInboundHandlerAdapter {
    private final static Logger logger= LoggerFactory.getLogger(MessageDecoder.class);
    //接受客户端消息上报
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf= (ByteBuf) msg;
        BaseMessage message=MessageUtils.getBaseMessage(buf);
        logger.info("接受到客户端消息:{}", JSON.toJSONString(message));
        //回复消息
        BaseMessage responseMsg=new BaseMessage(new Date(),"服务器已经收到您的消息："+message.getMessageContent(),
                message.getMessageId()+1);
        logger.info("回复消息给客户端:{}", JSON.toJSONString(responseMsg));
        ByteBuf byteBuf=MessageUtils.getByteBuf(responseMsg);
        ctx.writeAndFlush(byteBuf);
    }
    /**
     * 客户端连接会触发
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("Channel active......");
        super.channelActive(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        logger.info("Channel channelRegistered......");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        logger.info("Channel channelRegistered......");
        super.channelUnregistered(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.info("Channel exceptionCaught......");
        super.exceptionCaught(ctx, cause);
    }
}
