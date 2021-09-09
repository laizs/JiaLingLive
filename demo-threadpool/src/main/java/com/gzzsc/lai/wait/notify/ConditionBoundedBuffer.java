package com.gzzsc.lai.wait.notify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @className ConditionBoundedBuffer
 * @Deacription 使用ReentrantLock的显式条件condition来实现有界缓存的功能
 * @Author laizs
 * @Date 2021/7/7 16:30
 **/
public class ConditionBoundedBuffer<V> {
    private final Lock lock=new ReentrantLock();
    //条件谓词：notFull
    private final Condition notFull=lock.newCondition();
    //条件谓词：notEmpty
    private final Condition notEmpty=lock.newCondition();
    private final V[] items;
    private int tail;
    private int head;
    private int count;
    public ConditionBoundedBuffer(int capacity){
        items= (V[]) new Object[capacity];
    }
    public void put(V v) throws InterruptedException {
        lock.lock();
        try {
            while (count==items.length){
                //如果队列已满，则阻塞等等
                System.out.println("队列已满，put阻塞");
                notFull.await();
            }
            items[tail]=v;
            if(++tail==items.length){
                tail=0;
            }
            ++count;
            System.out.println("put数据："+v);
            //数据存放成功，唤醒take信号
            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }
    public V take() throws InterruptedException {
        lock.lock();
        try {
            while(count==0){
                System.out.println("队列为空，take阻塞");
                //如果队列为空，则阻塞等等
                notEmpty.await();
            }
            V v=items[head];
            if(++head==items.length){
                head=0;
            }
            --count;
            System.out.println("take数据："+v);
            //数据出队列，则唤醒put信号
            notFull.signal();
            return v;
        }finally {
            lock.unlock();
        }
    }
    //测试
    public static void main(String[] args) throws InterruptedException {
        ConditionBoundedBuffer<String> bb=new ConditionBoundedBuffer<>(1);
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bb.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bb.put("1");
        bb.put("2");
    }

}
