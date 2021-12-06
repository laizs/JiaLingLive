package com.gzzsc.lai.controller;

import com.gzzsc.lai.cfg.MyWatcher;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * @className WatcherBlockRunner
 * @Deacription TODO
 * @Author laizs
 * @Date 2021/11/24 13:17
 **/
public class WatcherBlockRunner implements  Runnable{
    private String name;
    private ZooKeeper zk;
    private MyWatcher myWatcher;
    private CountDownLatch cdl;
    public WatcherBlockRunner(String name,ZooKeeper zk,MyWatcher myWatcher,CountDownLatch cdl){
        this.name=name;
        this.zk=zk;
        this.myWatcher=myWatcher;
        this.cdl=cdl;
    }
    @Override
    public void run() {
        String path="/test";
        try {
            zk.exists(path, myWatcher);
            print("我是："+name+",开始监视/test");
            print("我是："+name+",开始阻塞...");
            cdl.await();
            print("我是："+name+",被唤醒.....");

        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    private void print(String msg){
        System.out.println(msg);
    }
}
