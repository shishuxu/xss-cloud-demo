package com.xss.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *  用户表(TTestName)实体类
 * @author xss
 * @date 2024/0225
 * @version 1.0
 * @since 2024-02-25 16:36:39
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestName implements Serializable {
    private static final long serialVersionUID = 729949468518424966L;
    /**
     * id
     */
    private Integer id;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 名字
     */
    private String name;
    /**
     * 更新时间
     */
    private Long createTime;


}

