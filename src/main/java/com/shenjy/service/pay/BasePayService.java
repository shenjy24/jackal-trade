package com.shenjy.service.pay;

import com.shenjy.common.OperationResult;
import com.shenjy.dto.pay.PayNotifyInfo;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 第三方支付顶层接口
 * 1、处理支付回调通知
 * 2、创建支付元数据
 * 3、查询支付结果
 *
 * @author shenjy 2019/06/01
 */
public interface BasePayService {

    /**
     * 校验第三方支付回调通知
     *
     * @param payNotifyMap 第三方通知参数
     * @return
     */
    OperationResult<PayNotifyInfo> validatePayNotify(Map<String, String> payNotifyMap);

    /**
     * 创建第三方支付元数据
     *
     * @param flowId    流水号
     * @param payPrice  支付金额
     * @param goodsDesc 商品描述
     * @param userIp    用户IP
     * @return
     */
    Map<String, Object> createPayMetaData(Long flowId, BigDecimal payPrice, String goodsDesc, String userIp);

}
