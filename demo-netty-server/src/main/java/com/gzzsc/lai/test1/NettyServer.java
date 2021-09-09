package com.gzzsc.lai.test1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @className NettyServer
 * @Deacription 服务端程序
 * @Author laizs
 * @Date 2021/8/25 16:16
 **/
public class NettyServer {
    private final static Logger logger= LoggerFactory.getLogger(NettyServer.class);
    private int port;
    public NettyServer(int port){
        this.port=port;
    }

    /**
     * 服务绑定端口及启动逻辑
     */
    public void start() {
        //创建BossGroup和WorkerGroup
        EventLoopGroup boss=new NioEventLoopGroup();
        EventLoopGroup worker=new NioEventLoopGroup();
        try {
            //创建ServerBootstrap
            ServerBootstrap bootstrap=new ServerBootstrap();
            bootstrap.group(boss,worker);
            //设置channel和option
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.option(ChannelOption.SO_BACKLOG,1024);//设置BACKLOG大小为1024
            bootstrap.option(ChannelOption.SO_KEEPALIVE,true);//启动心跳检测程序
            bootstrap.option(ChannelOption.TCP_NODELAY,true);//设置数据包无延迟
            bootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel socketChannel) throws Exception {
                    ChannelPipeline p=socketChannel.pipeline();
                    //定义MessageDecoder,用来解码server端接受到的消息并处理
                    p.addLast("decoder",new MessageDecoder());
                }
            });
            //绑定端口号并启动
            ChannelFuture channelFuture= bootstrap.bind(port).sync();
            if(channelFuture.isSuccess()){
                logger.info("Nettey Server 启动成功，端口:{}",port);
            }
            //设置异步关闭连接
            channelFuture.channel().closeFuture().sync();

        }catch (Exception e){
            logger.error("Nettey Server 启动失败，exception:"+e.getMessage());
            e.printStackTrace();
        }finally {
            //优雅退出函数设置
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new NettyServer(9999).start();
    }
}
