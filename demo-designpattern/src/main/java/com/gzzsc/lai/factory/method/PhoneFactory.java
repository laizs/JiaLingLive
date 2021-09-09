package com.gzzsc.lai.factory.method;

/**
 * @className PhoneFactory
 * @Deacription 抽象的手机工厂
 * @Author laizs
 * @Date 2021/8/20 13:25
 **/
public interface PhoneFactory {
    /**
     * 所谓的工厂方法
     * @return
     */
    Phone createPhone();
}
