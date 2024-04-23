package com.xss.seata.controller;

import com.xss.seata.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/dept/")
public class DeptController {
    @Autowired
    private DeptService deptService;

    @RequestMapping("testSeata")
    public Object testSeata(@RequestParam(value = "isException") boolean isException) {
        deptService.testSeata(isException);
        return "ok";
    }


}

