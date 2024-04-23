package com.xss.seataorder.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * @description 测试分布式事务
 * @author xss
 * @version 1.0
 * @date 2024/04/13
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Goods implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品标题
     */
    private String goodsTitle;

    /**
     * 商品图片
     */
    private String goodsImg;

    /**
     * 商品详情
     */
    private String goodsDetail;

    /**
     * 商品单价
     */
    private BigDecimal goodsPrice;

    /**
     * 商品库存
     */
    private Integer goodsStock;

    /**
     * 是否设为秒杀
     */
    private Integer isSeckill;

    private static final long serialVersionUID = 1L;


}