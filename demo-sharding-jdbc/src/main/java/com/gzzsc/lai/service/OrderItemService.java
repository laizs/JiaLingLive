package com.gzzsc.lai.service;

import com.gzzsc.lai.entity.OrderItem;

import java.util.List;

/**
 * @ClassName OrderIntemService
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/6/24 17:40
 **/
public interface OrderItemService {
    void save(OrderItem orderItem);
    List<OrderItem> findAll();
}
