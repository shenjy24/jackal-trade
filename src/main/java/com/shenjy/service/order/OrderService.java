package com.shenjy.service.order;

import com.shenjy.entity.order.Order;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shenjy
 * @since 2019-05-10
 */
public interface OrderService extends IService<Order> {

    /**
     * 获取用户订单
     *
     * @param userId
     * @return
     */
    List<Order> listOrder(Long userId);

    /**
     * 创建用户订单
     *
     * @param userId
     * @return
     */
    Boolean saveOrder(Long userId);
}
