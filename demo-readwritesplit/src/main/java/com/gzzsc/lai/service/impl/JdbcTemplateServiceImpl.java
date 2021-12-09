package com.gzzsc.lai.service.impl;

import com.gzzsc.lai.annotation.DataSourceMaster;
import com.gzzsc.lai.annotation.DataSourceSlave;
import com.gzzsc.lai.service.JdbcTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @className JdbcTemplateServiceImpl
 * @Deacription TODO
 * @Author laizs
 * @Date 2021/12/7 15:03
 **/
@Service
public class JdbcTemplateServiceImpl implements JdbcTemplateService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    @DataSourceSlave
    public List<Map<String, Object>> getAllUsers() {
        String sql="select * from user ";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    @DataSourceMaster
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public int deleteById(long id) {
        String sql="delete from user where id=? ";
        int r=jdbcTemplate.update(sql,id);
        int s=1/0;
        return r;
    }

}
