package com.xss.seataorder.service;


import com.xss.seataorder.entity.Goods;
import com.xss.seataorder.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author xu457
 * @description 针对表【goods】的数据库操作Service
 * @createDate 2024-04-21 19:19:08
 */
@Service
public class GoodsService {
    @Autowired
    public GoodsMapper goodMapper;

    public void add() {
        Goods goods = Goods.builder()
                .goodsName("seata")
                .goodsTitle("seata")
                .goodsImg("img")
                .goodsDetail("seata")
                .goodsPrice(new BigDecimal("0.2"))
                .goodsStock(11)
                .isSeckill(2)
                .build();
        goodMapper.insert(goods);
    }


}
