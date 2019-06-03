package com.shenjy.service.order.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.SystemClock;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenjy.entity.order.OrderBase;
import com.shenjy.enums.order.OrderStatusEnum;
import com.shenjy.mapper.order.OrderBaseMapper;
import com.shenjy.service.order.OrderBaseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 基础订单表 服务实现类
 * </p>
 *
 * @author shenjy
 * @since 2019-06-01
 */
@Service
public class OrderBaseServiceImpl extends ServiceImpl<OrderBaseMapper, OrderBase> implements OrderBaseService {

    @Override
    public List<OrderBase> listOrderBase(Long uid, String outTradeId, Integer orderStatus) {
        QueryWrapper<OrderBase> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(OrderBase::getUid, uid);
        wrapper.lambda().eq(OrderBase::getOrderId, outTradeId);
        wrapper.lambda().eq(OrderBase::getOrderStatus, orderStatus);

        return baseMapper.selectList(wrapper);
    }

    @Override
    public boolean updateOrderBase(Long orderId, OrderStatusEnum oldStatus, OrderStatusEnum newStatus) {
        UpdateWrapper<OrderBase> wrapper = new UpdateWrapper<>();
        wrapper.lambda().eq(OrderBase::getOrderStatus, oldStatus);
        wrapper.lambda().eq(OrderBase::getOrderId, orderId);

        wrapper.lambda().set(OrderBase::getOrderStatus, newStatus);
        wrapper.lambda().set(OrderBase::getPayTime, SystemClock.now());

        return this.update(wrapper);
    }
}
