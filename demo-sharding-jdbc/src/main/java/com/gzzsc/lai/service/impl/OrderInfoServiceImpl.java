package com.gzzsc.lai.service.impl;

import com.gzzsc.lai.entity.OrderInfo;
import com.gzzsc.lai.mapper.OrderInfoMapper;
import com.gzzsc.lai.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName OrderInfoServiceImpl
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/6/24 17:38
 **/
@SuppressWarnings("ALL")
@Service("orderInfoService")
public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Override
    public List<OrderInfo> findAll() {
        return this.orderInfoMapper.selectByExample(null);
    }

    @Override
    public void save(OrderInfo orderInfo) {
            this.orderInfoMapper.insert(orderInfo);
    }
}
