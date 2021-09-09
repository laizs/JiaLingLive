package com.gzzsc.lai.singleton;

/**
 * @className Singleton1
 * @Deacription 静态内部类实现的单例模式
 * @Author laizs
 * @Date 2021/8/20 11:42
 **/
public class Singleton1 {

    private Singleton1(){
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("初始化单例...");

    }
    //静态背部类
    private static class SingletonHolder{
        //静态变量
        private static final Singleton1 instance=new Singleton1();
    }
    public static Singleton1 getInstance(){
        return SingletonHolder.instance;
    }

    public static void main(String[] args) {
        for(int i=0;i<5;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Singleton1.getInstance();
                }
            }).start();
        }
    }

}
