package com.shenjy.service.order;

import com.shenjy.entity.order.OrderBase;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shenjy.enums.order.OrderStatusEnum;

import java.util.List;

/**
 * <p>
 * 基础订单表 服务类
 * </p>
 *
 * @author shenjy
 * @since 2019-06-01
 */
public interface OrderBaseService extends IService<OrderBase> {

    List<OrderBase> listOrderBase(Long uid, String outTradeId, Integer orderStatus);

    boolean updateOrderBase(Long orderId, OrderStatusEnum oldStatus, OrderStatusEnum newStatus);
}
