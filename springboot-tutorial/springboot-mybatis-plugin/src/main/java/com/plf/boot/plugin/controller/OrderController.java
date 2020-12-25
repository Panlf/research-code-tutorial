package com.plf.boot.plugin.controller;

import com.plf.boot.plugin.bean.Order;
import com.plf.boot.plugin.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author panlf
 * @date 2020/12/25
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("get/{id}")
    public Order getOrder(@PathVariable long id){
        return orderService.findById(id);
    }
}
