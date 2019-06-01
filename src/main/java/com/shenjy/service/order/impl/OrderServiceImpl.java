package com.shenjy.service.order.impl;

import com.shenjy.entity.order.Order;
import com.shenjy.mapper.order.OrderMapper;
import com.shenjy.service.order.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shenjy
 * @since 2019-05-10
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Override
    public List<Order> listOrder(Long userId) {
        return baseMapper.listOrder(userId);
    }

    @Override
    public Boolean saveOrder(Long userId) {
        Order order = new Order();
        order.setUserId(userId);
        return this.save(order);
    }
}
