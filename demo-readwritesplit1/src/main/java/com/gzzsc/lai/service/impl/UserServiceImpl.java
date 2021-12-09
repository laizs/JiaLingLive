package com.gzzsc.lai.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gzzsc.lai.mapper.UserMapper;
import com.gzzsc.lai.provider.entity.User;
import com.gzzsc.lai.provider.entity.UserExample;
import com.gzzsc.lai.service.UserService;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public User getById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     *
     * @param user
     */
    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
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
    public List<User> search(Long id, String username) {
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
    public Page<User> searchPage(Long id, String username, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        UserExample userExample=new UserExample();
        UserExample.Criteria c= userExample.or();
        if(null!=id){
            c.andIdEqualTo(id);
        }
        if(StringUtils.isNotBlank(username)){
            c.andUsernameLike("%"+username+"%");
        }
        userExample.setOrderByClause("  id desc ");
        Page<User> p= (Page<User>) this.userMapper.selectByExample(userExample);
        return p;
    }

    /**
     * 删除所有
     *
     * @return
     */
    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
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
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public int deleteById(Long id) {
        int r=0;
        r=this.userMapper.deleteByPrimaryKey(id);
        int cc=1/0;
        return r;
    }

    /**
     * 根据username删除
     *
     * @param username
     * @return
     */
    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
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
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
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
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public int updatePassByUserName(String username, String password) {
        UserExample example=new UserExample();
        UserExample.Criteria c=example.or();
        c.andUsernameEqualTo(username);
        User u=new User();
        u.setPassword(password);
        return this.userMapper.updateByExampleSelective(u,example);
    }

    /**
     * 根据配置id查询
     *
     * @param cfgId
     * @return
     */
    @Override
    public List<User> findByCfgId(Long cfgId) {
        return this.userMapper.findByCfgId(cfgId);
    }

    /**
     * 测试join表
     *
     * @return
     */
    @Override
    public List<User> findAllByVersion() {
        return  this.userMapper.findAllByVersion();
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public User testUpdateAndFind(User user) {
        this.userMapper.selectByPrimaryKey(user.getId());//在更新语句查询一次，看走主库还是从库，这里是走从库
        this.userMapper.updateByPrimaryKey(user);
        return this.userMapper.selectByPrimaryKey(user.getId());//这里是走主库
    }

    @Override
    public User testUpdateAndFindNoTransaction(User user) {
        this.userMapper.selectByPrimaryKey(user.getId());//没事务，是走从库
        this.userMapper.updateByPrimaryKey(user);
        return this.userMapper.selectByPrimaryKey(user.getId());//没事务，是走从库
    }


}
