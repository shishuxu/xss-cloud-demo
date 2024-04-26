package com.xss.seata.service;

import com.xss.parent.feign.GoodsFeignService;
import com.xss.seata.entity.Dept;
import com.xss.seata.mapper.DeptMapper;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeptService  {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private GoodsFeignService goodsFeignService;
    //    全局事务的入口 需要添加这个注解


    @GlobalTransactional
    public void testSeata(boolean isException) {
        Dept dept = new Dept();
        dept.setName("Java高级工1程师" + 1);
        dept.setSalary("140K");
        dept.setCity("北京");
        deptMapper.insert(dept);
        goodsFeignService.add(isException);
    }


}
