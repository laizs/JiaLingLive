package com.gzzsc.lai.factory.pattern;

/**
 * @className Main
 * @Deacription TODO
 * @Author laizs
 * @Date 2021/8/20 14:11
 **/
public class Main {
    public static void main(String[] args) {
        AbstractFactory hw=new HuaweiFactory();
        Product hwPhone=hw.createPhone();
        hwPhone.introduce();
        Product hwComputer=hw.createComputer();
        hwComputer.introduce();
        AbstractFactory apple=new AppleFactory();
        Product applePhone=apple.createPhone();
        applePhone.introduce();
        Product appleComputer=apple.createComputer();
        appleComputer.introduce();
    }
}
