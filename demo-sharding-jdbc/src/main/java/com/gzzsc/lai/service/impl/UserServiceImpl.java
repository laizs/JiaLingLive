package com.gzzsc.lai.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gzzsc.lai.entity.User;
import com.gzzsc.lai.entity.UserExample;
import com.gzzsc.lai.mapper.UserMapper;
import com.gzzsc.lai.service.UserService;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Deacription 用户信息service
 * @Author laizs
 * @Date 2020/5/26 15:35
 **/
@SuppressWarnings("ALL")
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    /**
     * 获取用户
     *
     * @param id
     * @return
     */
    @Override
    public User getById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     *
     * @param user
     */
    @Override
    public void save(User user) {
        //this.userMapper.insert(user);
        this.userMapper.insertSelective(user);
    }

    /**
     * 查询所有数据
     *
     * @return
     */
    @Override
    public List<User> getAll() {
        UserExample userExample=new UserExample();
        userExample.setOrderByClause(" id desc");
        return this.userMapper.selectByExample(userExample);
    }

    /**
     * 搜索
     *
     * @param id
     * @param username
     * @return
     */
    @Override
    public List<User> search(Integer id, String username) {
        UserExample userExample=new UserExample();
        UserExample.Criteria c= userExample.or();
        if(null!=id){
            c.andIdEqualTo(id);
        }
        if(StringUtils.isNotBlank(username)){
            c.andUsernameLike("%"+username+"%");
        }
        return this.userMapper.selectByExample(userExample);
    }

    /**
     * 分页搜索
     *
     * @param id
     * @param username
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public Page<User> searchPage(Integer id, String username, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        UserExample userExample=new UserExample();
        UserExample.Criteria c= userExample.or();
        if(null!=id){
            c.andIdEqualTo(id);
        }
        if(StringUtils.isNotBlank(username)){
            c.andUsernameLike("%"+username+"%");
        }
        Page<User> p= (Page<User>) this.userMapper.selectByExample(userExample);
        return p;
    }

    /**
     * 删除所有
     *
     * @return
     */
    @Override
    public int deleteAll() {
       return  this.userMapper.deleteByExample(null);
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @Override
    public int deleteById(int id) {
        return this.userMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据username删除
     *
     * @param username
     * @return
     */
    @Override
    public int deleteByUsername(String username) {
        UserExample example=new UserExample();
        UserExample.Criteria c= example.or();
        if(StringUtils.isNotBlank(username)){
            c.andUsernameEqualTo(username);
        }
        return this.userMapper.deleteByExample(example);
    }

    /**
     * 更新
     *
     * @param user
     * @return
     */
    @Override
    public int update(User user) {
        return this.userMapper.updateByPrimaryKey(user);
    }

    /**
     * 根据username更新password
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public int updatePassByUserName(String username, String password) {
        UserExample example=new UserExample();
        UserExample.Criteria c=example.or();
        c.andUsernameEqualTo(username);
        User u=new User();
        u.setPassword(password);
        return this.userMapper.updateByExampleSelective(u,example);
    }


}
