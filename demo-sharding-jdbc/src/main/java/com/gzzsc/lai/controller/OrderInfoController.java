package com.gzzsc.lai.controller;

import com.gzzsc.lai.provider.entity.OrderInfo;
import com.gzzsc.lai.provider.entity.OrderItem;
import com.gzzsc.lai.service.OrderInfoService;
import com.gzzsc.lai.service.OrderItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName OrderInfoController
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/6/24 17:45
 **/
@Api(tags = "订单服务接口")
@RestController
@RequestMapping("/order")
public class OrderInfoController {
    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private OrderItemService orderItemService;
    @ApiOperation("查询所有订单")
    @GetMapping("")
    public List<OrderInfo> findAll(){
        return this.orderInfoService.findAll();
    }
    @ApiOperation("批量保存数据")
    @PostMapping("/saveAll")
    public String saveAll(){
        for(int i=1;i<=10;i++){
            OrderInfo orderInfo=new OrderInfo();
            orderInfo.setName("order"+i);
            orderInfo.setUid((long)i);
            this.orderInfoService.save(orderInfo);
        }
        return "success";
    }
    @ApiOperation("删除所有的订单")
    @DeleteMapping("")
    public int deleteAll(){
        return this.orderInfoService.deleteAll();
    }
    @ApiOperation("批量保存订单目录")
    @PostMapping("/saveOrderItems")
    public String saveOrderItems(){
        for(int i=1;i<=10;i++){
            OrderItem orderItem=new OrderItem();
            //orderInfo.setOid((long)i);
           // orderItem.setItemId((long)i);
            orderItem.setOid((long)i);
            orderItem.setUid((long)i);
            this.orderItemService.save(orderItem);
        }
        return "success";
    }
    @ApiOperation("删除所有的订单目录")
    @DeleteMapping("/deleteAllOrderItems")
    public int deleteOrderItems(){
        return this.orderItemService.deleteAll();
    }
    @ApiOperation("查询所有的订单目录")
    @GetMapping("/getALlOrderItems")
    public List<OrderItem> getALlOrderItems(){
        return this.orderItemService.findAll();
    }
    @ApiOperation("查询订单目录详情信息")
    @GetMapping("/findItemDetails")
    public List<Map> findItemDetails(@RequestParam(name = "name",required = false) String name){
        return this.orderItemService.findItemDetails(name);
    }
    @ApiOperation("根据订单id查询目录详情列表")
    @GetMapping("/findItemDetailsByOid")
    public List<Map> findItemDetailsByOid(@RequestParam(name="iod",required = false) Long oid){
        return this.orderItemService.findItemDetailsByOid(oid);
    }
    @ApiOperation("根据uid查询订单")
    @GetMapping("/findOrderInfosByUid")
    public List<OrderInfo> findOrderInfosByUid(@RequestParam(name = "uid",required = false) Long uid){
        return this.orderInfoService.findByUid(uid);
    }
    @ApiOperation("根据uid查询订单")
    @GetMapping("/findOrderItemsByUid")
    public  List<OrderItem> findOrderItemsByUid(@RequestParam(name="uid",required = false) Long uid){
            return this.orderItemService.findByUid(uid);
    }
    @ApiOperation("根据map查询订单目录详情")
    @GetMapping("/findItemDetailsByMap")
    public List<Map> findItemDetailsByMap(String name,Long uid,Long oid){
        Map map=new HashMap();
        map.put("name",name);
        map.put("uid",uid);
        map.put("oid",oid);
        return this.orderItemService.findItemDetailsByMap(map);
    }
}
