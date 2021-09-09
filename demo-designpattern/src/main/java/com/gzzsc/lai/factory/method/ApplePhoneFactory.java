package com.gzzsc.lai.factory.method;

/**
 * @className ApplePhoneFactory
 * @Deacription 具体产品工厂类，苹果手机工厂
 * @Author laizs
 * @Date 2021/8/20 13:29
 **/
public class ApplePhoneFactory implements PhoneFactory {
    @Override
    public Phone createPhone() {
        return new ApplePhone();
    }
}
