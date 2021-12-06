package com.gzzsc.lai.service.impl;

import com.gzzsc.lai.mapper.LoginRecordMapper;
import com.gzzsc.lai.provider.entity.LoginRecord;
import com.gzzsc.lai.provider.entity.LoginRecordExample;
import com.gzzsc.lai.service.LoginRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName LoginRecordServiceImpl
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/6/30 16:57
 **/
@SuppressWarnings("ALL")
@Service("loginRecordService")
public class LoginRecordServiceImpl implements LoginRecordService {
    @Autowired
    private LoginRecordMapper loginRecordMapper;
    /**
     * @return
     */
    @Override
    public List<LoginRecord> findAll() {
        return loginRecordMapper.selectByExample(null);
    }

    /**
     * @param loginRecord
     */
    @Override
    public void save(LoginRecord loginRecord) {
        this.loginRecordMapper.insertSelective(loginRecord);
    }

    /**
     * @return
     */
    @Override
    public int deleteAll() {
        return this.loginRecordMapper.deleteByExample(null);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public LoginRecord getById(Long id) {
        return this.loginRecordMapper.selectByPrimaryKey(id);
    }

    /**
     * @param dateStr
     * @return
     */
    @Override
    public List<LoginRecord> findDateStr(String dateStr) {
        LoginRecordExample example=new LoginRecordExample();
        example.or().andDateStrEqualTo(dateStr);
        return this.loginRecordMapper.selectByExample(example);
    }

    /**
     * @param dateStr
     * @return
     */
    @Override
    public int updateAccountByDateStr(String account,String dateStr){
        return this.loginRecordMapper.updateAccountByDateStr(account,dateStr);
    }

    /**
     * @param dateStr
     * @return
     */
    @Override
    public int deleteByDateStr(String dateStr) {
        LoginRecordExample example=new LoginRecordExample();
        example.or().andDateStrEqualTo(dateStr);
        return this.loginRecordMapper.deleteByExample(example);
    }

    /**
     * 根据日期模糊查询
     *
     * @param dateStr
     * @return
     */
    @Override
    public List<LoginRecord> findByDateStrLike(String dateStr) {
        return this.loginRecordMapper.findByDateStrLike(dateStr);
    }

    /**
     * @param dateStr1
     * @param dateStr2
     * @return
     */
    @Override
    public List<LoginRecord> findByDateStrBetween(String dateStr1, String dateStr2) {
        return this.loginRecordMapper.findByDateStrBetween(dateStr1,dateStr2);
    }
}
