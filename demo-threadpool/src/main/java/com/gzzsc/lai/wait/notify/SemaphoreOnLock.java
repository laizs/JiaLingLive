package com.gzzsc.lai.wait.notify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @className SemaphoreOnLock
 * @Deacription 使用ReentrantLock实现Semaphore功能
 * 并非java.util.concurrent.Semaphore的真正实现
 * @Author laizs
 * @Date 2021/7/7 15:19
 **/
public class SemaphoreOnLock {
    private  final Lock lock=new ReentrantLock();
    private final Condition permitsAvailable=lock.newCondition();//条件谓词
    private int permits;

    /**
     * 构造函数
     * @param initPermits
     */
    public SemaphoreOnLock(int initPermits){
        lock.lock();
        try {
            this.permits=initPermits;
        }finally {
            lock.unlock();
        }
    }

    /**
     * 申请信号
     */
    public void acquire() throws InterruptedException {
        lock.lock();
        try {
            while (permits<=0){
                permitsAvailable.await();//阻塞
            }
            //申请到信号
            permits--;
        }finally {
            lock.unlock();
        }
    }

    /**
     * 释放信号量
     */
    public void release(){
        lock.lock();
        try {
            ++permits;
            permitsAvailable.signal();
        }finally {
            lock.unlock();
        }
    }
    //测试
    public static void main(String[] args) {
        SemaphoreOnLock semaphore=new SemaphoreOnLock(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire();//这一步会阻塞
                    System.out.println("第一个线程执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire();//这一步会阻塞
                    System.out.println("第二个线程执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    semaphore.release();//这一步会阻塞
                    System.out.println("释放一个信号量");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public class MyThread extends  Thread{
        public MyThread(String name){
            super(name);
        }
        @Override
        public void run() {
            super.run();
        }
    }

}
