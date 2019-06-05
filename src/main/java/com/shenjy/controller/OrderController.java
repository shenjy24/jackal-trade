package com.shenjy.controller;


import com.shenjy.enums.pay.PayApiEnum;
import com.shenjy.service.order.OrderManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
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
    private OrderManageService orderManageService;

    /**
     * 创建订单
     *
     * @param uid       用户id
     * @param goodsDesc 商品描述
     * @param price     价格
     * @param payApiId  支付实现
     * @param sourceTradeId 内部的订单号
     * @param userIp    用户IP
     * @return
     */
    @PostMapping("/createOrder")
    public Map<String, Object> createOrder(@NotNull(message = "用户id不能为空") Long uid,
                                           @NotBlank(message = "商品描述不能为空") String goodsDesc,
                                           @NotNull(message = "支付金额不能为空") BigDecimal price,
                                           @NotNull(message = "支付方式不能为空") Long payApiId,
                                           @NotNull(message = "交易号不能为空") String sourceTradeId,
                                           String userIp) {
        return orderManageService.createOrder(uid, goodsDesc, price, PayApiEnum.getEnum(payApiId), sourceTradeId, userIp);
    }
}

