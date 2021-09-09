package com.gzzsc.lai;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @className ReadWriteLockTest
 * @Deacription 读写锁测试
 * @Author laizs
 * @Date 2021/8/12 10:33
 **/
public class ReadWriteLockTest {
    private final Map<String,Object> cache= new HashMap<>();//共享数据
    private final ReentrantReadWriteLock rwLock=new ReentrantReadWriteLock();
    private final Lock readLock=rwLock.readLock();//读锁
    private final Lock writeLock=rwLock.writeLock();//写锁

    public Object get(String key){
        readLock.lock();
        try{
            return cache.get(key);
        }finally {
            readLock.unlock();
        }
    }

    public Object put(String key,Object value){
        writeLock.lock();
        try {
            Object o=cache.put(key,value);
            for(int i=0;i<3;i++){
                System.out.println("我要在写锁中阻塞调读锁---");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return o;
        }finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLockTest t=new ReadWriteLockTest();
        //一个线程不断的读数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                   Object v= t.get("kay");
                   System.out.println("get  数据："+v);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        //一个线程修改数据并休眠
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    t.put("kay",System.nanoTime());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
