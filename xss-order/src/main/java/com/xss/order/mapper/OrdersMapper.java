package com.xss.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xss.order.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Date 23/0407
 * @author xss
 * @version 1.0
 */
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

    @Select("select * from orders where order_type>4")
    List<Orders> get();

}
