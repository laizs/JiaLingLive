package com.gzzsc.lai.singleton;

/**
 * @className Singleton2
 * @Deacription 使用双重校验锁方式创建单例模式
 * @Author laizs
 * @Date 2021/8/20 11:51
 **/
public class Singleton2 {
    //必须加volatile变量，使用volatile内存屏障的作用，防止指令重排导致得到instance对象是一个未完整初始化的对象
    private static volatile  Singleton2 instance=null;
    private Singleton2(){
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("使用双重校验锁方式创建单例模式...");
    }
    public static final Singleton2 getInstance(){
        if(null==instance){//第一次检测
            synchronized (Singleton2.class){//枷锁代码块
                if(null==instance){//第二次检测
                    instance=new Singleton2();
                }
            }
        }
        return instance;
    }
    public static void main(String[] args) {
        for(int i=0;i<5;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Singleton2.getInstance();
                }
            }).start();
        }
    }
}
