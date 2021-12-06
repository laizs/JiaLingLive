package com.gzzsc.lai.service;

import com.gzzsc.lai.provider.entity.LoginRecord;

import java.util.List;

/**
 * @ClassName LoginRecordService
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/6/30 16:56
 **/
public interface LoginRecordService {
    /**
     *
     * @return
     */
    List<LoginRecord> findAll();

    /**
     *
     * @param loginRecord
     */
    void save(LoginRecord loginRecord);

    /**
     *
     * @return
     */
    int deleteAll();

    /**
     *
     * @param id
     * @return
     */
    LoginRecord getById(Long id);

    /**
     *
     * @param dateStr
     * @return
     */
    List<LoginRecord> findDateStr(String dateStr);

    /**
     *
     * @param account
     * @param dateStr
     * @return
     */
    int updateAccountByDateStr(String account,String dateStr);

    /**
     *
     * @param dateStr
     * @return
     */
    int deleteByDateStr(String dateStr);
    /**
     * 根据日期模糊查询
     * @param dateStr
     * @return
     */
    List<LoginRecord> findByDateStrLike(String dateStr);

    /**
     *
     * @param dateStr1
     * @param dateStr2
     * @return
     */
    List<LoginRecord> findByDateStrBetween(String dateStr1,String dateStr2);
}
