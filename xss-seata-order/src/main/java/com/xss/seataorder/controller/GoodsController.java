package com.xss.seataorder.controller;

import com.xss.seataorder.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/goods/")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;


    @RequestMapping("add")
    public String add(@RequestParam(value = "isException") boolean isException) {
        goodsService.add();
        if (isException) {
            throw new RuntimeException("调用错误");
        }
        return "ok";
    }

}

