package com.xss.order.controller;

import com.google.gson.Gson;
import com.xss.order.entity.Orders;
import com.xss.order.mapper.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 测试分表中间件实体类
 * @Date 23/0407
 * @author xss
 * @version 1.0
 */
@RequestMapping("api/shardingHere/")
@RestController
public class ShardingsphereController {
    @Autowired
    private OrdersMapper ordersMapper;

    /**
     * 测试分表中间件 插入数据
     * @return
     */
    @GetMapping(value = "addOrders",produces = MediaType.APPLICATION_JSON_VALUE)
    public String addOrders(){
        for (int i = 1; i <=10 ; i++) {
            Orders orders = new Orders();
            orders.setId(i);
            orders.setCustomerId(i);
            orders.setOrderType(i);
            orders.setAmount(1000.0*i);
            ordersMapper.insert(orders);
        }
        return "ok";
    }

    @GetMapping(value = "queryOrders",produces = MediaType.APPLICATION_JSON_VALUE)
    public String queryOrders(){
        List<Orders> list = ordersMapper.get();
        System.out.println(list);

        return new Gson().toJson(list);
    }
}
