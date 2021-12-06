package com.gzzsc.lai.cfg;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

/**
 * @className ZooWatchedEvent
 * @Deacription TODO
 * @Author laizs
 * @Date 2021/11/24 12:59
 **/
public class MyWatcher implements Watcher {
    String name;
    CountDownLatch cdl;
    public MyWatcher(String name){
        this.name=name;
    }
    public MyWatcher(String name, CountDownLatch cdl){
        this.name=name;
        this.cdl=cdl;
    }
    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("监视器："+name+"-事件->"+watchedEvent.getPath()+":"+watchedEvent.getType()+":"+watchedEvent.getState());
        if(cdl!=null){
            cdl.countDown();
        }
    }
}
