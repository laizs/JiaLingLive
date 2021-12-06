package com.gzzsc.lai.cfg;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @className ZooCfg
 * @Deacription TODO
 * @Author laizs
 * @Date 2021/11/24 12:43
 **/
@Configuration
public class ZooCfg {
    public static String zkServer="192.168.111.25:2181,192.168.111.26:2181,192.168.111.27:2181";
    public static final int connectTimeout = 3000;
    @Bean
    public ZooKeeper zooKeeper(){
        ZooKeeper zk=null;
        try {
             zk=new ZooKeeper(zkServer, connectTimeout, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println("zk连接成功");

                }
            });
            System.out.println("zk 状态："+zk.getState());

        } catch (IOException e) {
            e.printStackTrace();

        }
        return zk;
    }
}
