package com.gzzsc.lai.factory.method;

/**
 * @className ApplePhone
 * @Deacription 苹果手机
 * @Author laizs
 * @Date 2021/8/20 13:21
 **/
public class ApplePhone implements  Phone{
    @Override
    public void call() {
        System.out.println("苹果手机呼叫...");
    }
}
