package com.gzzsc.lai;

import java.util.concurrent.*;

/**
 * @className BoundedExecutor
 * @Deacription 使用信号量来控制线程池提交任务的速率
 * @Author laizs
 * @Date 2021/7/1 14:10
 **/
public class BoundedExecutor {
    private final ExecutorService executor;
    private final Semaphore semaphore;
    public BoundedExecutor(ExecutorService executor,int bound){
        this.executor=executor;
        this.semaphore=new Semaphore(bound);
    }
    public void submitTask(final Runnable command) throws InterruptedException {
        semaphore.acquire();
        try {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        command.run();
                    }finally {
                        semaphore.release();
                    }
                }
            });
        }catch (RejectedExecutionException e){
            semaphore.release();
        }

    }

    public static void main(String[] args) {
            ExecutorService executor= Executors.newCachedThreadPool();
            BoundedExecutor boundedExecutor=new BoundedExecutor(executor,2);
            for(int i=1;i<10;i++){
                try {
                    boundedExecutor.submitTask(new MyRunnable(i+"",20000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

    }


}
