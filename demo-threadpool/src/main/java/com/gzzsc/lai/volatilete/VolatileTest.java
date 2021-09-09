package com.gzzsc.lai.volatilete;

/**
 * @className VolatileTest
 * @Deacription volatile测试
 * volatile提供内存屏障和内存可见性特性，
 * @Author laizs
 * @Date 2021/7/12 13:28
 **/
public class VolatileTest {
    private  volatile int sum=0;//很难测出来

    public static void main(String[] args) {
        VolatileTest v=new VolatileTest();

        //线程2
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程2-sum:"+ v.sum);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
               System.out.println("线程2-sum:"+ v.sum);
            }
        }).start();
        //线程1
        new Thread(new Runnable() {
            @Override
            public void run() {
                v.sum=2;//注意需要原子操作，sum++这种不是原子操作
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
