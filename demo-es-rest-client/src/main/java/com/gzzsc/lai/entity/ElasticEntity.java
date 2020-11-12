package com.gzzsc.lai.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @ClassName ElasticEntity
 * @Deacription 数据存储对象
 * @Author laizs
 * @Date 2020/9/9 17:14
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElasticEntity<T> {
    /**
     * 主键标识，同于ES持久化
     */
    private String id;
    /**
     * json对象，实际存储数据
     */
    private Map data;
}
