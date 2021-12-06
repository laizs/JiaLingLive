package com.gzzsc.lai.mapper;

import com.gzzsc.lai.provider.entity.User;
import com.gzzsc.lai.provider.entity.UserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

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
}