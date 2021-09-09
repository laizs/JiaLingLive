package com.gzzsc.lai.test1;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @className MessageUtils
 * @Deacription 消息处理工具
 * @Author laizs
 * @Date 2021/8/25 16:07
 **/
public class MessageUtils {
    /**
     * 将message写入ByteBuf
     * @param baseMessage
     * @return
     */
    public static ByteBuf getByteBuf(BaseMessage baseMessage) throws UnsupportedEncodingException {
        byte[] req= JSON.toJSONString(baseMessage).getBytes("UTF-8");
        ByteBuf byteBuf= Unpooled.buffer();
        byteBuf.writeBytes(req);
        return byteBuf;
    }

    /**
     * 从ByteBuf中获取信息，使用utf-8编码后解析成BaseMessage对象
     * @param byteBuf
     * @return
     */
    public static BaseMessage getBaseMessage(ByteBuf byteBuf){
        byte[] con=new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(con);
        try {
            String message=new String(con,"UTF-8");
            BaseMessage baseMessage=JSON.parseObject(message,BaseMessage.class);
            baseMessage.setReceiveTime(new Date());
            return baseMessage;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
