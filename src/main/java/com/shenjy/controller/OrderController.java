package com.shenjy.controller;


import com.shenjy.entity.order.Order;
import com.shenjy.entity.order.OrderBase;
import com.shenjy.enums.pay.PayApiEnum;
import com.shenjy.service.order.OrderManageService;
import com.shenjy.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author shenjy
 * @since 2019-05-10
 */
@Validated
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderManageService orderManageService;

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

    /**
     * 创建订单
     *
     * @param uid       用户id
     * @param goodsDesc 商品描述
     * @param price     价格
     * @param payApiId  支付实现
     * @param inTradeId 内部的订单号
     * @param userIp    用户IP
     * @return
     */
    @PostMapping("/createOrder")
    public Map<String, Object> createOrder(@NotNull(message = "用户id不能为空") Long uid,
                                           @NotBlank(message = "商品描述不能为空") String goodsDesc,
                                           @NotNull(message = "支付金额不能为空") BigDecimal price,
                                           @NotNull(message = "支付方式不能为空") Long payApiId,
                                           @NotNull(message = "交易号不能为空") String inTradeId,
                                           String userIp) {
        return orderManageService.createOrder(uid, goodsDesc, price, PayApiEnum.getEnum(payApiId), inTradeId, userIp);
    }
}

