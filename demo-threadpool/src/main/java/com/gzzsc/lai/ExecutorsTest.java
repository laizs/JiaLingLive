package com.gzzsc.lai;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @className ExecutorsTest
 * @Deacription Executors创建线程测试
 * @Author laizs
 * @Date 2021/6/30 14:41
 **/
public class ExecutorsTest {
    public static void main(String[] args) {
        //newSingleThreadPoolTest();
        //newCachedThreadPoolTest();
        newFiexThreadPoolTest();
    }

    /**
     * corePoolSize=1 maxinumPoolSize=1  blockingQueue= new LinkedBlockingQueue();blockingQueue容量大小默认是Integer.MAX_VALUE
     * keepAliveIime 0 表示非核心线程空闲时间为0就被销毁  既非核心线程创建完并执行完任务之后，即刻销毁
     * 此处核心线程是1  非核心线程是0  所以keepAliveIime参数没有作用意义
     * 简义：单线程，阻塞队列大小Integer.MAX_VALUE；创建的线程不回收
     */
    public static void newSingleThreadPoolTest(){

        ExecutorService executor= Executors.newSingleThreadExecutor();
        for(int i=1;i<=100;i++){
            executor.submit(new MyRunnable(i+"",1000));
        }
    }
    /**
     * corePoolSize=0 maxinumPoolSize=Integer.MAX_VALUE  blockingQueue= new SynchronousQueue();
     * keepAliveIime 60  unit=TimeUnit.SECONDS 表示非核心线程空闲时间超过60秒后被销毁
     * SynchronousQueue SynchronousQueue没有容量，是无缓冲等待队列，是一个不存储元素的阻塞队列，会直接将任务交给消费者，必须等队列中的添加元素被消费后才能继续添加新的元素。
     * 说明提交任务时不能存放在队列中，直接提交给线程
     * 简义：可缓存线程池，当提交任务时，没有空闲的线程执行任务，则创建新线程，不使用队列来缓存任务，线程数最大容量Integer.MAX_VALUE；线程当空闲时间超过60秒则被回收
     */
    public static void newCachedThreadPoolTest(){
        ThreadPoolExecutor executor= (ThreadPoolExecutor) Executors.newCachedThreadPool();
        for(int i=1;i<=10;i++){
            executor.submit(new MyRunnable(i+"",10000));
        }
        while (true){
            System.out.println("当天总线程数："+ executor.getPoolSize()+"活跃线程数:"+executor.getActiveCount());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * corePoolSize=nThreads maxinumPoolSize=nThreads  blockingQueue= new LinkedBlockingQueue();blockingQueue容量大小默认是Integer.MAX_VALUE
     * keepAliveIime 0 表示非核心线程空闲时间为0就被销毁  既非核心线程创建完并执行完任务之后，即刻销毁
     * 此处核心线程是=最大容量缓存，没有非核心线程  所以keepAliveIime参数没有作用意义
     * 简义：固定大小线程池，阻塞队列大小Integer.MAX_VALUE;创建的线程不回收
     */
    public static void newFiexThreadPoolTest(){
        ThreadPoolExecutor executor= (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        for(int i=1;i<=10;i++){
            executor.submit(new MyRunnable(i+"",10000));
        }
        while (true){
            System.out.println("当天总线程数："+ executor.getPoolSize()+"活跃线程数:"+executor.getActiveCount());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
