package com.xss.user.mapper;

import com.xss.user.entity.TestName;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TestNameMapper {
    /**
     * 插入
     * @param testName
     * @return
     */
    int insert(TestName testName);


}
