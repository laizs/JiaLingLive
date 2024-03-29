package com.gzzsc.lai.service;

import com.github.pagehelper.Page;
import com.gzzsc.lai.provider.entity.Doctor;
import com.gzzsc.lai.provider.entity.User;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
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
     * 批量保存，验证事务异常
     * @param users
     */
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    void saveAllWithException(List<User> users);

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
    /**
     * 测试join表
     * @return
     */
    List<User> findAllByVersion();
    /**
     * 批量保存，多表保存，验证事务异常
     * @param users
     */
    @ShardingTransactionType(TransactionType.LOCAL)
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    void saveUsersAndDoctorsWithException(List<User> users, List<Doctor> doctors);
}
