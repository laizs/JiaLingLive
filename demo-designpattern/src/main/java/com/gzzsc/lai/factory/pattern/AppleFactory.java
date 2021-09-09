package com.gzzsc.lai.factory.pattern;

/**
 * @className AppleFactory
 * @Deacription 苹果工厂
 * @Author laizs
 * @Date 2021/8/20 14:16
 **/
public class AppleFactory implements AbstractFactory{
    @Override
    public Product createPhone() {
        return new ApplePhone();
    }

    @Override
    public Product createComputer() {
        return new AppleComputer();
    }
}
