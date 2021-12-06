package com.gzzsc.lai.service;

import com.gzzsc.lai.provider.entity.OrderItem;

import java.util.List;
import java.util.Map;

/**
 * @ClassName OrderIntemService
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/6/24 17:40
 **/
public interface OrderItemService {
    void save(OrderItem orderItem);
    List<OrderItem> findAll();
    int deleteAll();
    /**
     * 查询目录详情列表
     * @return
     */
    List<Map> findItemDetails(String name);

    /**
     * 根据订单id查询订单目录详情
     * @param oid
     * @return
     */
    List<Map> findItemDetailsByOid(Long oid);
    List<OrderItem> findByUid(Long uid);
    /**
     * 根据map查询订单目录详情
     * @param map
     * @return
     */
    List<Map> findItemDetailsByMap(Map map);
}
