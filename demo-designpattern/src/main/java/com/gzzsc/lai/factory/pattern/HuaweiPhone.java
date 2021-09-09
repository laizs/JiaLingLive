package com.gzzsc.lai.factory.pattern;

/**
 * @className HuaweiPhone
 * @Deacription 华为手机  具体产品
 * @Author laizs
 * @Date 2021/8/20 14:03
 **/
public class HuaweiPhone extends  Phone {
    @Override
    public void introduce() {
        System.out.println("我就是华为手机");
    }
}
