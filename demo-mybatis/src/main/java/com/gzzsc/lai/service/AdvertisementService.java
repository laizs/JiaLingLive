package com.gzzsc.lai.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName AdvertisementService
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/5/27 16:19
 **/
public interface AdvertisementService {
    /**
     * 根据id删除
     * @param id
     * @return
     */
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    int deleteById(String id);
}
