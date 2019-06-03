package com.shenjy.service.order;

import com.shenjy.enums.pay.PayApiEnum;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 【 enter the class description 】
 *
 * @author shenjy 2019/06/02
 */
public interface OrderManageService {
    Map<String, Object> createOrder(Long uid, String goodsDesc, BigDecimal price, PayApiEnum payApiEnum, String inTradeId, String userIp);
}
