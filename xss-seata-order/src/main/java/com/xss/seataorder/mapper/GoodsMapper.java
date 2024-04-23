package com.xss.seataorder.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xss.seataorder.entity.Goods;
import org.springframework.stereotype.Repository;

/**
* @author xu457
* @description 针对表【goods】的数据库操作Mapper
* @createDate 2024-04-21 19:19:08
* @Entity com.xss.seataorder.entity.Goods
*/
@Repository
public interface GoodsMapper extends BaseMapper<Goods> {


}
