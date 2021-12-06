package com.gzzsc.lai.controller;

import com.gzzsc.lai.cfg.MyWatcher;
import org.apache.zookeeper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;

/**
 * @className ZooTestController
 * @Deacription TODO
 * @Author laizs
 * @Date 2021/11/24 12:45
 **/
@RestController
public class ZooTestController {
    @Autowired
    private ZooKeeper zooKeeper;
    @RequestMapping("/testConnect")
    public String testConnect(){
        zooKeeper.getState();
        return "s";
    }
    private MyWatcher myWatcher=new MyWatcher("public");
    @RequestMapping("/testDel")
    public String testDel(){
        try {
            final String path="/test";
            //创建节点
            String p=zooKeeper.create(path,"0".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println("创建节点:"+p);
            //测试监视两次节点被删除事件，同一个监视器
            //---结果只响应了一次事件
            zooKeeper.exists(path, myWatcher);
            zooKeeper.exists(path, myWatcher);
            zooKeeper.exists(path, new MyWatcher("my1"));
            zooKeeper.exists(path, new MyWatcher("my2"));

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        zooKeeper.delete(path,-1);
                        System.out.println("删除节点");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ss";
    }
    @RequestMapping("/testConcurrent")
    public void testConcurrent(){
        try {
            final String path="/test";
            //创建节点
            String p=zooKeeper.create(path,"0".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println("创建节点:"+p);
            //启动三个监视线程，三个线程监视之后就开始阻塞，等待被唤醒
            CountDownLatch cld1=new CountDownLatch(1);
            new Thread(new WatcherBlockRunner("w1",zooKeeper,new MyWatcher("mw1",cld1),cld1)).start();
            CountDownLatch cld2=new CountDownLatch(1);
            new Thread(new WatcherBlockRunner("w2",zooKeeper,new MyWatcher("mw2",cld2),cld1)).start();



            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        zooKeeper.delete(path,-1);
                        System.out.println("删除节点");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
