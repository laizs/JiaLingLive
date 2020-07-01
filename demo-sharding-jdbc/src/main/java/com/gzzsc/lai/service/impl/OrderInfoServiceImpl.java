package com.gzzsc.lai.service.impl;

import com.gzzsc.lai.entity.OrderInfo;
import com.gzzsc.lai.entity.OrderInfoExample;
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
            this.orderInfoMapper.insertSelective(orderInfo);
    }

    @Override
    public int deleteAll() {
        return this.orderInfoMapper.deleteByExample(null);
    }

    @Override
    public List<OrderInfo> findByUid(Long uid) {
        OrderInfoExample example=new OrderInfoExample();
        if(null!=uid){
            example.or().andUidEqualTo(uid);
        }
        return this.orderInfoMapper.selectByExample(example);
    }
}
