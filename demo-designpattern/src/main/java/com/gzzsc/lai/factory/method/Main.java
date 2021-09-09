package com.gzzsc.lai.factory.method;

/**
 * @className Main
 * @Deacription TODO
 * @Author laizs
 * @Date 2021/8/20 13:32
 **/
public class Main {
    public static void main(String[] args) {
        PhoneFactory factory=new ApplePhoneFactory();
        Phone phone=factory.createPhone();//隐藏产品创建细节
        phone.call();
        factory=new HuaweiPhoneFactory();
        phone=factory.createPhone();
        phone.call();
    }
}
