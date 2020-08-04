package com.gzzsc.lai.customer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName UserInfo
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/7/17 15:41
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private String id;
    private String name;
    private String phone;
    private int sex;
    private int age;
    private double height;
}
