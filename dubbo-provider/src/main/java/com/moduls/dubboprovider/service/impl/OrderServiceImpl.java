package com.moduls.dubboprovider.service.impl;

import com.moduls.dubboprovider.mapper.OrderMapper;
import com.moduls.dubboprovider.pojo.Orders;
import com.moduls.dubboprovider.service.OrderProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderProviderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Integer addOrders(Orders orders) {
        return orderMapper.addOrders(orders);
    }
}
