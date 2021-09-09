package com.gzzsc.lai.factory.method;

/**
 * @className HuaweiPhone
 * @Deacription 华为手机
 * @Author laizs
 * @Date 2021/8/20 13:22
 **/
public class HuaweiPhone implements Phone{
    @Override
    public void call() {
        System.out.println("华为手机呼叫...");
    }
}
