package com.gzzsc.lai.service;

import java.util.List;
import java.util.Map;

/**
 * @className JdbcTemplateService
 * @Deacription 测试jdbcTemplate读写分离
 * @Author laizs
 * @Date 2021/12/7 15:01
 **/
public interface JdbcTemplateService {
    List<Map<String, Object>> getAllUsers();
    int deleteById(long id);
}
