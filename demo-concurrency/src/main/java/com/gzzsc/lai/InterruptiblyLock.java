package com.gzzsc.lai;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @className InterruptiblyLock
 * @Deacription 测试 ReentrantLock提供的可中断锁特性，用于有效消除死锁
 * @Author laizs
 * @Date 2021/8/11 14:12
 **/
public class InterruptiblyLock {
    public ReentrantLock lock1=new ReentrantLock();
    public ReentrantLock lock2=new ReentrantLock();

    /**
     * lock1 与 lock2 方法构成死锁
     * @return
     */
    public Thread lock1(){
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock1.lockInterruptibly();//如果当前线程未中断，可获取锁
                    try {
                        Thread.sleep(500);//模拟执行自己的业务
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    lock2.lockInterruptibly();//尝试获取locck2锁
                    System.out.println(Thread.currentThread().getName()+",执行完毕!");
                } catch (InterruptedException e) {//可捕获线程中断的异常
                    e.printStackTrace();
                }finally {
                    if(lock1.isHeldByCurrentThread()){
                        lock1.unlock();//检查当前线程是否持有改锁，如果有则释放锁
                    }
                    if(lock2.isHeldByCurrentThread()){
                        lock2.unlock();
                    }
                    System.out.println(Thread.currentThread().getName()+",退出!");
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
                try {
                    lock2.lockInterruptibly();//如果当前线程未中断，可获取锁
                    try {
                        Thread.sleep(500);//模拟执行自己的业务
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    lock1.lockInterruptibly();//尝试获取locck2锁
                    System.out.println(Thread.currentThread().getName()+",执行完毕!");
                } catch (InterruptedException e) {//可捕获线程中断的异常
                    e.printStackTrace();
                }finally {
                    if(lock1.isHeldByCurrentThread()){
                        lock1.unlock();//检查当前线程是否持有改锁，如果有则释放锁
                    }
                    if(lock2.isHeldByCurrentThread()){
                        lock2.unlock();
                    }
                    System.out.println(Thread.currentThread().getName()+",退出!");
                }
            }
        });
        t.start();
        return t;
    }

    public static void main(String[] args) {
        long time =System.currentTimeMillis();
        InterruptiblyLock interruptiblyLock=new InterruptiblyLock();
        Thread t1=interruptiblyLock.lock1();
        Thread t2=interruptiblyLock.lock2();
        //没有以下代码，运行则t1/t2进入死锁
        //自旋一段时间，如果等待时间过长，则可能发生了死锁等问题，主动中断并释放锁
        while (true){
            if(System.currentTimeMillis()-time>=3000){
                t2.interrupt();//中断t2
                System.out.println("中断t2");//此时线程可以响应中断，因为ReentrantLock的可中断锁状态
                break;
            }
        }
    }
}
