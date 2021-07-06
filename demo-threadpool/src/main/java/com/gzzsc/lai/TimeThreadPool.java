package com.gzzsc.lai;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

/**
 * @className TimeThreadPool
 * @Deacription 添加了统计和加时功能的线程池
 * @Author laizs
 * @Date 2021/7/1 10:13
 **/
public class TimeThreadPool extends ThreadPoolExecutor {
    //使用ThreadLocal变量记录开始时间
    private final ThreadLocal<Long> startTime=new ThreadLocal<>();
    private Logger logger=Logger.getLogger("TimeThreadPool");
    //执行总任务数
    private final AtomicLong numTasks=new AtomicLong();
    //执行总时间
    private final AtomicLong totalTime=new AtomicLong();
    public TimeThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        startTime.set(System.nanoTime());
        System.out.println(String.format("当前线程：%s>> Thread %s:start %s",Thread.currentThread(),t,r));
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        try {
            long endTime=System.nanoTime();
            long taskTime=endTime-startTime.get();
            numTasks.incrementAndGet();
            totalTime.addAndGet(taskTime);
            System.out.println(String.format("当前线程：%s>> Thread %s:start %s,time=%dns",Thread.currentThread(),t,r,taskTime));
        }finally {
            super.afterExecute(r, t);
        }

    }
    @Override
    protected void terminated() {
        try {
            System.out.println(String.format("当前线程：%s>> Terminaled: avg time=%dns",Thread.currentThread(),totalTime.get()/numTasks.get()));
        }finally {
            super.terminated();
        }
    }
}
