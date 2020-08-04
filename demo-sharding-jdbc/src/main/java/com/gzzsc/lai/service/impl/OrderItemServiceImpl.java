package com.gzzsc.lai.service.impl;

import com.gzzsc.lai.provider.entity.OrderItem;
import com.gzzsc.lai.provider.entity.OrderItemExample;
import com.gzzsc.lai.mapper.OrderItemMapper;
import com.gzzsc.lai.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
        this.orderItemMapper.insertSelective(orderItem);
    }

    @Override
    public List<OrderItem> findAll() {
        return this.orderItemMapper.selectByExample(null);
    }

    @Override
    public int deleteAll() {
        return this.orderItemMapper.deleteByExample(null);
    }

    /**
     * 查询目录详情列表
     *
     * @param name
     * @return
     */
    @Override
    public List<Map> findItemDetails(String name) {
        return this.orderItemMapper.findItemDetails(name);
    }

    /**
     * 根据订单id查询订单目录详情
     *
     * @param oid
     * @return
     */
    @Override
    public List<Map> findItemDetailsByOid(Long oid) {
        return this.orderItemMapper.findItemDetailsByOid(oid);
    }

    @Override
    public List<OrderItem> findByUid(Long uid) {
        OrderItemExample example=new OrderItemExample();
        if(null!=uid){
            example.or().andUidEqualTo(uid);
        }
        return this.orderItemMapper.selectByExample(example);
    }

    /**
     * 根据map查询订单目录详情
     *
     * @param map
     * @return
     */
    @Override
    public List<Map> findItemDetailsByMap(Map map) {
        return this.orderItemMapper.findItemDetailsByMap(map);
    }
}
