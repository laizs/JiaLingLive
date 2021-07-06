package com.gzzsc.lai;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @className ReentrantLock
 * @Deacription ReentrantLock测试
 * @Author laizs
 * @Date 2021/7/5 15:50
 **/
public class ReentrantLockTest {
    private ReentrantLock lock=new ReentrantLock();
    /**
     * 测试方法
     */
    public void testLock(){
        if(lock.tryLock()){
            try {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }finally {
                lock.unlock();
            }
        }
    }
    public void testLock(long tryTime) throws InterruptedException {
        if(lock.tryLock(tryTime, TimeUnit.SECONDS)){
            try {
                try {
                    System.out.println("线程："+Thread.currentThread()+"---获得了锁");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }finally{
                lock.unlock();
            }
        }else{
            System.out.println("线程："+Thread.currentThread()+"^^^^^^获取锁超时");
        }
    }

    public static void main(String[] args) {
        ReentrantLockTest rt=new ReentrantLockTest();
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    rt.testLock(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"MyThread1");
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    rt.testLock(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"MyThread2");
        t1.start();
        t2.start();


    }
}
