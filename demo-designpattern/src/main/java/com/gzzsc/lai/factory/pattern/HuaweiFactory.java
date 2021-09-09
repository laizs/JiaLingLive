package com.gzzsc.lai.factory.pattern;

/**
 * @className HuaweiFactory
 * @Deacription 具体工厂
 * @Author laizs
 * @Date 2021/8/20 14:11
 **/
public class HuaweiFactory implements AbstractFactory {
    @Override
    public Product createPhone() {
        return new HuaweiPhone();
    }

    @Override
    public Product createComputer() {
        return new HuaweiComputer();
    }
}
