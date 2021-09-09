package com.gzzsc.lai.factory.pattern;

/**
 * @className Factory
 * @Deacription 抽象工厂类,可以创建多种类产品
 * @Author laizs
 * @Date 2021/8/20 14:06
 **/
public interface AbstractFactory {
    Product createPhone();
    Product createComputer();
}
