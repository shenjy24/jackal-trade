package com.shenjy.service.order.impl;

import com.shenjy.entity.order.OrderBase;
import com.shenjy.mapper.order.OrderBaseMapper;
import com.shenjy.service.order.OrderBaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
