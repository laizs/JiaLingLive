package com.gzzsc.lai.provider.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName User
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/7/17 15:01
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private String name;
    private String phone;
    private int sex;
    private int age;
    private double height;

}
