package com.gzzsc.lai.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class User {
    @ApiModelProperty(value = "用户id",required = true,dataType="int",notes = "用户的唯一id",example = "1")
    private Long id;
    @ApiModelProperty(value = "用户名",dataType="String",notes = "用户名",example = "张三")
    private String username;
    @ApiModelProperty(value = "用户密码",dataType="String",notes = "用户密码",example = "888888")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}