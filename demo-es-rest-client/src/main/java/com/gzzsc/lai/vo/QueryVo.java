package com.gzzsc.lai.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @ClassName QueryVo
 * @Deacription 查询VO对象
 * @Author laizs
 * @Date 2020/9/10 15:54
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryVo {
    /**
     * 索引名称
     */
    private String idxName;
    /**
     * 需要反射的实体类型，用于对查询结果的封装
     */
    private String className;
    /**
     * 具体条件
     */
    private Map<String, Map<String,Object>> query;

}
