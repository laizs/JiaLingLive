package com.gzzsc.lai.factory.method;

/**
 * @className HuaweiPhoneFactory
 * @Deacription 华为手机工厂
 * @Author laizs
 * @Date 2021/8/20 13:31
 **/
public class HuaweiPhoneFactory implements  PhoneFactory{
    @Override
    public Phone createPhone() {
        return new HuaweiPhone();
    }
}
