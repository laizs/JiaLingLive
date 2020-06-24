package com.gzzsc.lai.mapper;

import com.gzzsc.lai.entity.OrderItem;
import com.gzzsc.lai.entity.OrderItemExample;
import java.util.List;
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
}