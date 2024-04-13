package com.xss.order.controller;

import com.xss.order.service.DeptService;
import com.xss.parent.feign.TestNameFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/dept/")
public class DeptController {
    @Autowired
    private TestNameFeignService testNameFeignService;

    @Autowired
    private DeptService deptService;

    @RequestMapping("save")
    public String saveDept() {
        deptService.saveDept();
        return "Save Successfully!";
    }

    @RequestMapping("getList")
    public String getList() {
        deptService.getList();
        throw new RuntimeException("运行错误");
//        return "Save Successfully!";
    }


    @RequestMapping("testFeign")
    public Object getList1() {
        return testNameFeignService.test();
    }
}

