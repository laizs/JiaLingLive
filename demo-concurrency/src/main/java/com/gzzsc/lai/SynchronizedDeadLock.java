package com.gzzsc.lai;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @className InterruptiblyLock
 * @Deacription 测试 ReentrantLock提供的可中断锁特性，用于有效消除死锁
 * @Author laizs
 * @Date 2021/8/11 14:12
 **/
public class SynchronizedDeadLock {
    public Object lock1=new Object();
    public Object lock2=new Object();

    /**
     * lock1 与 lock2 方法构成死锁
     * @return
     */
    public Thread lock1(){
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock1){
                    try {
                        Thread.sleep(500);//模拟执行自己的业务
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    synchronized(lock2){
                        //尝试获取locck2锁
                    }
                    System.out.println(Thread.currentThread().getName()+",执行完毕!");
                }

            }
        });
        t.start();
        return t;
    }
    /**
     * lock1 与 lock2 方法构成死锁
     * @return
     */
    public Thread lock2(){
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock2){
                    try {
                        Thread.sleep(500);//模拟执行自己的业务
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    synchronized(lock1){
                        //尝试获取locck2锁
                    }
                    System.out.println(Thread.currentThread().getName()+",执行完毕!");
                }

            }
        });
        t.start();
        return t;
    }

    public static void main(String[] args) {
        long time =System.currentTimeMillis();
        SynchronizedDeadLock interruptiblyLock=new SynchronizedDeadLock();
        Thread t1=interruptiblyLock.lock1();
        Thread t2=interruptiblyLock.lock2();
        //没有以下代码，运行则t1/t2进入死锁
        //自旋一段时间，如果等待时间过长，则可能发生了死锁等问题，主动中断并释放锁
        while (true){
            if(System.currentTimeMillis()-time>=3000){
                System.out.println("中断t2");//但其实线程无法响应，因为线程不是处于阻塞状态，而是处于竞争锁状态
                t2.interrupt();//中断t2
                break;
            }
        }
    }
}
