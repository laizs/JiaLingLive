package com.gzzsc.lai;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * @className ZooTest
 * @Deacription TODO
 * @Author laizs
 * @Date 2021/11/24 12:31
 **/
public class ZooTest {
    public static String zkServer="192.168.111.25:2181,192.168.111.26:2181,192.168.111.27:2181";
    public static final int connectTimeout = 3000;
    public static void main(String[] args) {
        zkConnect();
    }
    public static void zkConnect(){
        try {
            ZooKeeper zk=new ZooKeeper(zkServer, connectTimeout, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println("zk连接成功");

                }
            });
            System.out.println("zk 状态："+zk.getState());
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
