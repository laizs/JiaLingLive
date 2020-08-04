package com.gzzsc.lai.mapper;

import com.gzzsc.lai.provider.entity.OrderItem;
import com.gzzsc.lai.provider.entity.OrderItemExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface OrderItemMapper {
    int countByExample(OrderItemExample example);

    int deleteByExample(OrderItemExample example);

    int deleteByPrimaryKey(Long itemId);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    List<OrderItem> selectByExample(OrderItemExample example);

    OrderItem selectByPrimaryKey(Long itemId);

    int updateByExampleSelective(@Param("record") OrderItem record, @Param("example") OrderItemExample example);

    int updateByExample(@Param("record") OrderItem record, @Param("example") OrderItemExample example);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);

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

    /**
     * 根据map查询订单目录详情
     * @param map
     * @return
     */
    List<Map> findItemDetailsByMap(Map map);
}