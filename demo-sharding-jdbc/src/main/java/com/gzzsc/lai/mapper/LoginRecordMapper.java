package com.gzzsc.lai.mapper;

import com.gzzsc.lai.entity.LoginRecord;
import com.gzzsc.lai.entity.LoginRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LoginRecordMapper {
    int countByExample(LoginRecordExample example);

    int deleteByExample(LoginRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LoginRecord record);

    int insertSelective(LoginRecord record);

    List<LoginRecord> selectByExample(LoginRecordExample example);

    LoginRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LoginRecord record, @Param("example") LoginRecordExample example);

    int updateByExample(@Param("record") LoginRecord record, @Param("example") LoginRecordExample example);

    int updateByPrimaryKeySelective(LoginRecord record);

    int updateByPrimaryKey(LoginRecord record);

    /**
     * 根据日期更新用户账号
     * @param userAccount
     * @param dateStr
     * @return
     */
    int updateAccountByDateStr(String userAccount,String dateStr);

    /**
     * 根据日期模糊查询
     * @param dateStr
     * @return
     */
    List<LoginRecord> findByDateStrLike(String dateStr);

    /**
     * 根据日期范围查询
     * @param dateStr1
     * @param dateStr2
     * @return
     */
    List<LoginRecord> findByDateStrBetween(String dateStr1,String dateStr2);
}