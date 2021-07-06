package com.gzzsc.lai;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @className CallerRunsPolicyTest
 * @Deacription "由调用线程处理该任务"拒绝策略测试
 * @Author laizs
 * @Date 2021/6/30 16:32
 **/
public class CallerRunsPolicyTest {
    public static void main(String[] args) {
        BlockingQueue<Runnable> queue=new LinkedBlockingDeque<>(1);
        ThreadPoolExecutor executor=new ThreadPoolExecutor(5,5,0, TimeUnit.SECONDS,queue,new ThreadPoolExecutor.CallerRunsPolicy());
        for(int i=1;i<=20;i++){
            executor.submit(new MyRunnable(i+"",10000));
        }
    }
}
