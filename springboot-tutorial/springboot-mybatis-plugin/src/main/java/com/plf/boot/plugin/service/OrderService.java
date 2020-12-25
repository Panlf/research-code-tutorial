package com.plf.boot.plugin.service;

import com.plf.boot.plugin.bean.Order;
import com.plf.boot.plugin.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author panlf
 * @date 2020/12/25
 */
@Service
public class OrderService {
    @Resource
    private OrderMapper orderMapper;

    public Order findById(long id){
        return orderMapper.findById(id);
    }
}
