package com.gzzsc.lai;
/**
 * @ClassName MyRunnable
 * @Deacription 任务
 * @Author laizs
 * @Date 2021/6/29 23:38
 **/
public class MyRunnable implements  Runnable {
    private String name;
    private long sleep;
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    public MyRunnable(String name,long sleep){
        this.name=name;
        this.sleep=sleep;
    }
    @Override
    public void run() {
        String threadName=Thread.currentThread().getName();
        System.out.println("--线程："+threadName+"执行任务："+name+"--开始");
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--线程："+threadName+"执行任务："+name+"--结束");
    }
}