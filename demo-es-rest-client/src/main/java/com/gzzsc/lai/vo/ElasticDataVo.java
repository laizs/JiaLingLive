package com.gzzsc.lai.vo;

import com.gzzsc.lai.entity.ElasticEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ElasticDataVo
 * @Deacription http交互对象
 * @Author laizs
 * @Date 2020/9/10 16:00
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElasticDataVo<T> {
    /**
     * 索引名称
     */
    private String idxName;
    /**
     * 数据存储对象
     */
    private ElasticEntity elasticEntity;

}
