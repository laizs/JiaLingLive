package com.gzzsc.lai;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @className TimeThreadPoolTest
 * @Deacription TODO
 * @Author laizs
 * @Date 2021/7/1 10:32
 **/
public class TimeThreadPoolTest {
    public static void main(String[] args) {
        BlockingQueue<Runnable> queue=new LinkedBlockingDeque<>(1);
        TimeThreadPool executor=new TimeThreadPool(5,5,0, TimeUnit.SECONDS,queue);
        for(int i=1;i<=3;i++){
            executor.submit(new MyRunnable(i+"",2000));
        }
        executor.shutdown();
    }
}
