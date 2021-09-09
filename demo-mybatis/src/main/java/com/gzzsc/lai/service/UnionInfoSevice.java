package com.gzzsc.lai.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * @className UnionInfoSevice
 * @Deacription TODO
 * @Author laizs
 * @Date 2021/8/23 17:49
 **/
public interface UnionInfoSevice {
    /**
     * 测试一级缓存,springboot中，由于线程池的关系，需要加上事务，在方法内数据库查询的方法才会使用同一个sqlSession，一级缓存才生效
     */
    @Transactional(rollbackFor = Throwable.class)
    public void doTestOneCached();
    /**
     * 测试二级缓存
     */
    public void doTestSecondCached();

    /**
     * 测试更新
     * @param id
     */
    public void update(String id);
}
