package com.shenjy.service.pay.impl;

import com.jonas.service.localevent.LocalEventSender;
import com.shenjy.common.CachePrefix;
import com.shenjy.common.OperationResult;
import com.shenjy.dto.pay.PayNotifyInfo;
import com.shenjy.entity.order.OrderBase;
import com.shenjy.entity.pay.PayFlow;
import com.shenjy.entity.pay.PayNotify;
import com.shenjy.enums.order.OrderStatusEnum;
import com.shenjy.enums.pay.PayApiEnum;
import com.shenjy.enums.pay.PayStatusEnum;
import com.shenjy.handler.local.LocalTopic;
import com.shenjy.handler.local.msg.DeliverMsg;
import com.shenjy.service.pay.PayFlowService;
import com.shenjy.service.pay.PayManageService;
import com.shenjy.service.pay.PayNotifyService;
import com.shenjy.service.order.OrderBaseService;
import com.shenjy.service.payapi.*;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 【 enter the class description 】
 *
 * @author shenjy 2019/06/03
 */
@Service
public class PayManageServiceImpl implements PayManageService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private PayServiceFactory payServiceFactory;
    @Autowired
    private PayFlowService payFlowService;
    @Autowired
    private OrderBaseService orderBaseService;
    @Autowired
    private PayNotifyService payNotifyService;
    @Autowired
    private LocalEventSender localEventSender;

    @Override
    public boolean handlePayNotify(Map<String, String> payNotifyMap, PayApiEnum payApiEnum) {
        if (MapUtils.isEmpty(payNotifyMap) || null == payApiEnum) {
            return false;
        }

        //获取支付实现类
        BasePayService payService = payServiceFactory.getService(payApiEnum);
        if (null == payService) {
            return false;
        }

        //校验回调参数
        OperationResult<PayNotifyInfo> result = payService.validatePayNotify(payNotifyMap);
        if (!result.getSucc()) {
            return false;
        }

        PayNotifyInfo notifyInfo = result.getEntity();
        //获取流水
        PayFlow payFlow = notifyInfo.getPayFlow();
        // 平台支付流水号
        Long flowId = payFlow.getPayFlowId();
        // 回调金额
        BigDecimal notifyPayPrice = payFlow.getPayPrice();
        // 第三方支付流水号
        String outTradeId = payFlow.getOutTradeId();

        if (null == flowId || StringUtils.isBlank(outTradeId)) {
            return false;
        }

        //分布式锁
        String key = CachePrefix.PAY_NOTIFY_LOCK.getPrefix() + ":" + flowId + ":" + outTradeId;
        if (!redisTemplate.opsForValue().setIfAbsent(key, 1)) {
            return false;
        } else {
            redisTemplate.expire(key, CachePrefix.PAY_NOTIFY_LOCK.getTimeout(), TimeUnit.SECONDS);
        }

        // 获取基础订单
        OrderBase orderBase = orderBaseService.getById(payFlow.getOrderId());
        if (null == orderBase) {
            return false;
        }

        PayStatusEnum payStatus = notifyInfo.getPaySuccess() ? PayStatusEnum.SUCCESS : PayStatusEnum.FAILURE;
        OrderStatusEnum orderStatus = notifyInfo.getPaySuccess() ? OrderStatusEnum.SUCCESS : OrderStatusEnum.FAILURE;

        // 创建支付回调通知记录
        PayNotify payNotify = new PayNotify();
        payNotify.setMerchantId(payFlow.getMerchantId());
        payNotify.setPayApiId(payFlow.getPayApiId());
        payNotify.setPayWay(payFlow.getPayWay());
        payNotify.setOutAccount(notifyInfo.getUserAccount());
        payNotify.setOutTradeId(outTradeId);
        payNotify.setPayPrice(payFlow.getPayPrice());
        payNotify.setPayStatus(payStatus);
        payNotifyService.save(payNotify);

        // 更新支付流水，更新前的状态必须是待支付
        boolean flag1 = payFlowService.updatePayFlow(flowId, outTradeId, PayStatusEnum.UN_PAY, payStatus);
        if (!flag1) {
            return false;
        }

        // 更新订单，更新前的状态必须是待支付
        boolean flag2 = orderBaseService.updateOrderBase(orderBase.getOrderId(), OrderStatusEnum.UN_PAY, orderStatus);
        if (!flag2) {
            return false;
        }

        if (PayStatusEnum.SUCCESS.equals(payStatus)) {
            //异步发货
            localEventSender.publish(LocalTopic.TOPIC_DELIVER, new DeliverMsg(orderBase.getOrderId()));
        }

        return true;
    }
}
