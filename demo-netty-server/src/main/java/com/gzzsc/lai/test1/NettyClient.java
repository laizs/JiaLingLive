package com.gzzsc.lai.test1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @className NettyClient
 * @Deacription netty客户端
 * @Author laizs
 * @Date 2021/8/25 17:42
 **/
public class NettyClient {
    public final static Logger logger= LoggerFactory.getLogger(NettyClient.class);
    //服务器端口
    private int port=9999;
    //服务器地址
    private String host="localhost";

    public NettyClient(int port, String host) {
        this.port = port;
        this.host = host;
    }

    public void start() {
        EventLoopGroup eventLoopGroup=new NioEventLoopGroup();
        try {
            Bootstrap bootstrap=new Bootstrap();
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.SO_KEEPALIVE,true);
            bootstrap.group(eventLoopGroup);
            bootstrap.remoteAddress(host,port);
            bootstrap.handler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                    nioSocketChannel.pipeline().addLast(new NettyClientHandler());
                }
            });
            ChannelFuture channelFuture=bootstrap.connect(host,port).sync();
            if(channelFuture.isSuccess()){
                logger.info("连接服务器成功，服务器ip:{},端口:{}",host,port);
            }
            channelFuture.channel().closeFuture().sync();

        }catch (Exception e){
            logger.error("连接服务器异常:",e);
            e.printStackTrace();
        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        NettyClient nettyClient=new NettyClient(9999,"127.0.0.1");
        nettyClient.start();
    }
}
