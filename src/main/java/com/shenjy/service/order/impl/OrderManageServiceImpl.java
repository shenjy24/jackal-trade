package com.shenjy.service.order.impl;

import com.jonas.common.BizException;
import com.shenjy.common.BizErrorCode;
import com.shenjy.entity.order.OrderBase;
import com.shenjy.entity.pay.PayApi;
import com.shenjy.entity.pay.PayFlow;
import com.shenjy.entity.pay.PayMerchant;
import com.shenjy.enums.order.DeliverStatusEnum;
import com.shenjy.enums.order.OrderStatusEnum;
import com.shenjy.enums.pay.PayApiEnum;
import com.shenjy.enums.pay.PayStatusEnum;
import com.shenjy.service.order.OrderBaseService;
import com.shenjy.service.order.OrderManageService;
import com.shenjy.service.pay.*;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.shenjy.common.CachePrefix.ORDER_CREATE_LOCK;

/**
 * 【 enter the class description 】
 *
 * @author shenjy 2019/06/02
 */
@Service
public class OrderManageServiceImpl implements OrderManageService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private OrderBaseService orderBaseService;
    @Autowired
    private PayMerchantService merchantService;
    @Autowired
    private PayApiService payApiService;
    @Autowired
    private PayFlowService payFlowService;
    @Autowired
    private PayServiceFactory payServiceFactory;

    @Override
    public Map<String, Object> createOrder(Long uid, String goodsDesc, BigDecimal price, PayApiEnum payApiEnum, String inTradeId, String userIp) {
        /**redis锁控制*/
        String key = ORDER_CREATE_LOCK.getPrefix() + uid + ":" + inTradeId;
        Boolean flag = redisTemplate.opsForValue().setIfAbsent(key, 1);
        if (flag) {
            redisTemplate.expire(key, ORDER_CREATE_LOCK.getTimeout(), TimeUnit.SECONDS);
        } else {
            throw new BizException(BizErrorCode.NOT_REPEAT_PROCESS);
        }

        //判读订单是否已存在
        List<OrderBase> orderBases = orderBaseService.listOrderBase(uid, inTradeId, OrderStatusEnum.SUCCESS.getCode());
        if (CollectionUtils.isNotEmpty(orderBases)) {
            throw new BizException(BizErrorCode.ORDER_HAS_EXIST);
        }

        //获取商户
        PayApi payApi = payApiService.getPayApiById(payApiEnum.getCode());
        if (null == payApi) {
            throw new BizException(BizErrorCode.PAY_API_NOT_EXIST);
        }
        PayMerchant merchant = merchantService.getMerchantById(payApi.getMerchantId());
        if (null == merchant) {
            throw new BizException(BizErrorCode.PAY_MERCHANT_NOT_EXIST);
        }

        //保存订单
        OrderBase orderBase = new OrderBase();
        orderBase.setUid(uid);
        orderBase.setOrderPrice(price);
        orderBase.setPayPrice(price);
        orderBase.setInTradeId(inTradeId);
        orderBase.setPayApiId(payApiEnum);
        orderBase.setPayWay(payApiEnum.getPayWay());
        orderBase.setMerchantId(merchant.getMerchantId());
        orderBase.setDeliverStatus(DeliverStatusEnum.UN_DELIVER);
        orderBase.setOrderStatus(OrderStatusEnum.UN_PAY);
        orderBaseService.save(orderBase);

        //保存流水
        PayFlow payFlow = new PayFlow();
        payFlow.setOrderId(orderBase.getOrderId());
        payFlow.setMerchantId(merchant.getMerchantId());
        payFlow.setUid(uid);
        payFlow.setPayPrice(price);
        payFlow.setPayStatus(PayStatusEnum.UN_PAY);
        payFlow.setPayApiId(payApiEnum);
        payFlow.setPayWay(payApiEnum.getPayWay());
        payFlowService.save(payFlow);

        BasePayService payService = payServiceFactory.getService(payApiEnum);
        if (null == payService) {
            throw new BizException(BizErrorCode.PAY_API_NOT_EXIST);
        }

        return payService.createPayMetaData(payFlow.getFlowId(), price, goodsDesc, userIp);
    }
}
