package com.gzzsc.lai;

import java.util.concurrent.*;

/**
 * @className PoolExecutorTest
 * @Deacription 测试线程池构造函数ThreadPoolExecutor创建线程特性
 * 持续提交任务，先创建满corePoolSize数的线程并立即执行任务；如果线程数已经达到corePoolSize；则将任务放入阻塞队列；如果阻塞队列
 * 已经放慢放不下的情况下，则新创建线程，直至线程数满maxinumPoolSize;如果持续提交进来任务，加入阻塞队列已经满，则使用任务拒绝策略拒绝任务提交
 * @Author laizs
 * @Date 2021/6/30 13:59
 **/
public class PoolExecutorTest {
    public static void main(String[] args) {
        BlockingDeque<Runnable> blockingDeque=new LinkedBlockingDeque<>(3);
        ExecutorService executor=new ThreadPoolExecutor(3,5,1, TimeUnit.DAYS,blockingDeque);
        for(int i=1;i<=5;i++){
            executor.submit(new MyRunnable(i+"",10000));
        }
        executor.shutdown();

    }
}
