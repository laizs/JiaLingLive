package com.gzzsc.lai.service.impl;

import com.gzzsc.lai.entity.User;
import com.gzzsc.lai.mapper.UserMapper;
import com.gzzsc.lai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @className UserServiceImpl
 * @Deacription 用户service
 * @Author laizs
 * @Date 2021/1/15 11:25
 **/
@SuppressWarnings("ALL")
@Service("userService")
public class UserServiceImpl  implements UserService {
    @Autowired
    private UserMapper userMapper;
    public User getById(int id) {
        return this.userMapper.selectByPrimaryKey(id);
    }
}
