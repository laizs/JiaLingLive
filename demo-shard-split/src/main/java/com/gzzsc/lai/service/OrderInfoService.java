package com.gzzsc.lai.service;

import com.gzzsc.lai.provider.entity.OrderInfo;

import java.util.List;

/**
 * @ClassName OrderInfoService
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/6/24 17:37
 **/
public interface OrderInfoService {
    List<OrderInfo> findAll();
    void save(OrderInfo orderInfo);
    int deleteAll();
    List<OrderInfo> findByUid(Long uid);


}
