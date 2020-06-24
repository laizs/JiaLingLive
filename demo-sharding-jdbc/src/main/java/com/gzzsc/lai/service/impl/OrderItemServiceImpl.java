package com.gzzsc.lai.service.impl;

import com.gzzsc.lai.entity.OrderItem;
import com.gzzsc.lai.mapper.OrderItemMapper;
import com.gzzsc.lai.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName OrderIntemServiceImpl
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/6/24 17:41
 **/
@SuppressWarnings("ALL")
@Service("orderItemService")
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Override
    public void save(OrderItem orderItem) {
        this.orderItemMapper.insert(orderItem);
    }

    @Override
    public List<OrderItem> findAll() {
        return this.orderItemMapper.selectByExample(null);
    }
}
