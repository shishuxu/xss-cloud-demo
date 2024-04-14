package com.xss.order.controller;

import com.google.gson.Gson;
import com.xss.order.entity.Orders;
import com.xss.order.mapper.OrdersMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        List<Orders> list = ordersMapper.selectList(null)
                .stream().sorted(Comparator.comparing(Orders::getId).reversed())
                .collect(Collectors.toList());
        int a = CollectionUtils.isNotEmpty(list) ? list.get(0).getId() : 10;
        for (int i = a + 1; i <= a + 10; i++) {
            Orders orders = new Orders();
            orders.setId(i);
            orders.setCustomerId(i);
            orders.setOrderType(i);
            orders.setAmount(1000.0 * i);
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
