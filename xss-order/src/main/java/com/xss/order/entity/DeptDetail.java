package com.xss.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

/**
 * 测试分表中间件实体类
 * @Date 23/0407
 * @author xss
 * @version 1.0
 */
@TableName("dept_detail")
@Data
@ToString
public class DeptDetail {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long pid;

    private String description;

}

