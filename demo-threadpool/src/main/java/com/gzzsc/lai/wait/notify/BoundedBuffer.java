package com.gzzsc.lai.wait.notify;

import java.lang.reflect.WildcardType;

/**
 * @className BoundedBuffer
 * @Deacription 使用notifyall wait实现的有界阻塞队列
 * @Author laizs
 * @Date 2021/7/7 11:07
 **/
public class BoundedBuffer<V> extends BaseBoundedBuffer<V> {
    protected BoundedBuffer(int capacity) {
        super(capacity);
    }
    public synchronized void put(V v) throws InterruptedException {
        while (isFull()){
            System.out.println("队列已满，put阻塞");
            wait();//如果队列是满的，则放弃当前获取的对象锁，并将线程挂起
        }
        doPut(v);
        System.out.println("put数据："+v);
        notifyAll();//通知其他线程可以竞争锁并继续执行
    }
    public synchronized V take() throws InterruptedException {
        while(isEmpty()){
            System.out.println("队列为空，take阻塞");
            wait();
        }
        V v=doTake();
        System.out.println("take数据："+v);
        notifyAll();
        return v;
    }
    //测试
    public static void main(String[] args) {
        //testInterrupt();
        try {
            test();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 测试中断
     */
    public static void testInterrupt(){
        BoundedBuffer<String> bb=new BoundedBuffer<>(1);
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
        t.interrupt();//测试中断
    }
    /**
     * test
     */
    public static void test() throws InterruptedException {
        BoundedBuffer<String> bb=new BoundedBuffer<>(1);
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
