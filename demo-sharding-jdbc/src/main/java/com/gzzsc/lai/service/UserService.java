package com.gzzsc.lai.service;

import com.github.pagehelper.Page;
import com.gzzsc.lai.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName UserService
 * @Deacription 用户信息service
 * @Author laizs
 * @Date 2020/5/26 15:32
 **/
public interface UserService {
    /**
     * 获取用户
     * @param id
     * @return
     */
    User getById(Long id);

    /**
     * 新增
     * @param user
     */
    void save(User user);

    /**
     * 查询所有数据
     * @return
     */
    List<User> getAll();

    /**
     * 搜索
     * @param id
     * @param username
     * @return
     */
    List<User> search(Long id,String username);

    /**
     * 分页搜索
     * @param id
     * @param username
     * @param page
     * @param pageSize
     * @return
     */
    Page<User> searchPage(Long id,String username,int page,int pageSize);

    /**
     * 删除所有
     * @return
     */
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    int deleteAll();

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    int deleteById(Long id);

    /**
     * 根据username删除
     * @param username
     * @return
     */
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    int deleteByUsername(String username);

    /**
     * 更新
     * @param user
     * @return
     */
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    int update(User user);

    /**
     * 根据username更新password
     * @param username
     * @param password
     * @return
     */
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    int updatePassByUserName(String username,String password);
    /**
     * 根据配置id查询
     * @param cfgId
     * @return
     */
    List<User> findByCfgId(Long cfgId);
}
