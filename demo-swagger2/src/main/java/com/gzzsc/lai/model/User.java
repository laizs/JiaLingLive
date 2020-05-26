package com.gzzsc.lai.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName User
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/5/21 14:15
 **/
@Data
@ApiModel
public class User {
    @ApiModelProperty(value = "用户id",required = true,dataType="int",notes = "用户的唯一id",example = "1")
    private Integer id;
    @ApiModelProperty(value = "用户姓名",example = "张三")
    private String userName;
    @ApiModelProperty(value = "用户地址",example = "中新广州知识城")
    private String address;
}
