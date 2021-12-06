package com.gzzsc.lai.service;

import com.gzzsc.lai.provider.entity.User;
/**
 * @className UserService
 * @Deacription 用户service
 * @Author laizs
 * @Date 2021/1/15 11:22
 **/
public interface UserService {
    /**
     * 根据id获取
     * @param id
     * @return
     */
    User getById(int id);
}
