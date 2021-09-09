package com.gzzsc.lai.factory.pattern;

/**
 * @className HuaweiComputer
 * @Deacription 华为电脑，具体产品
 * @Author laizs
 * @Date 2021/8/20 14:04
 **/
public class HuaweiComputer  extends Computer{
    @Override
    public void introduce() {
        System.out.println("我就是华为笔记本");
    }
}
