package com.jonas.controller;


import com.jonas.entity.order.Order;
import com.jonas.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shenjy
 * @since 2019-05-10
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 获取用户订单
     *
     * @param userId
     * @return
     */
    @PostMapping("/listOrder")
    public List<Order> listOrder(Long userId) {
        List<Order> orders = orderService.listOrder(userId);
        return orders;
    }

    /**
     * 创建用户订单
     *
     * @param userId
     * @return
     */
    @PostMapping("/saveOrder")
    public Boolean saveOrder(Long userId) {
        return orderService.saveOrder(userId);
    }

    /**
     * 测试feign传参
     *
     * @param orderId
     * @param orders
     * @return
     */
    @PostMapping("/testOrder")
    public String testOrder(Long orderId, @RequestBody List<Order> orders) {
        return "order:" + orderId + orders.size();
    }
}

