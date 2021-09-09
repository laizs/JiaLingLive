package com.gzzsc.lai.factory.builder;

/**
 * @className Main
 * @Deacription TODO
 * @Author laizs
 * @Date 2021/8/20 15:01
 **/
public class Main {
    public static void main(String[] args) {
        Computer.Builder builder=new Computer.Builder();
        Computer c= builder.cpu("8核").memory("16G").screen("30寸").mainboard("华硕").build();
        //Computer c=new Computer();
        System.out.println(c);
    }
}
