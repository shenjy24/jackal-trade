package com.shenjy.service.pay;

import com.shenjy.dto.pay.PayMerchantInfo;
import com.shenjy.entity.pay.PayApi;
import com.shenjy.entity.pay.PayMerchant;
import com.vip.vjtools.vjkit.mapper.BeanMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * 第三方支付抽象类
 * 1、获取支付实现方式
 * 2、获取当前商户信息
 *
 * @author shenjy 2019/06/01
 */
public abstract class AbstractPayService implements BasePayService {

    /**
     * 服务端回调接口域名
     */
    @Value("${pay.host}")
    protected String payHost;

    @Autowired
    protected PayApiService payApiService;
    @Autowired
    protected PayMerchantService payMerchantService;
    @Autowired
    protected PayFlowService payFlowService;

    /**
     * 获取支付实现方式
     *
     * @return
     */
    protected abstract Long getPayApiId();

    /**
     * 获取当前商户信息
     *
     * @return
     */
    protected PayMerchantInfo getCurrentPayMerchant() {
        Long payApiId = getPayApiId();
        if (null == payApiId) {
            throw new RuntimeException("支付实现类未配置pay_api_id");
        }

        PayApi payApi = payApiService.getById(payApiId);
        if (null == payApi || null == payApi.getMerchantId() || StringUtils.isBlank(payApi.getNotifyUrl())) {
            throw new RuntimeException("支付实现表pay_api未配置对应的商户ID或支付回调地址");
        }

        PayMerchant payMerchant = payMerchantService.getById(payApi.getMerchantId());
        if (null == payMerchant) {
            throw new RuntimeException("未配置相应商户,merchantId: " + payApi.getMerchantId());
        }

        PayMerchantInfo payMerchantInfo = BeanMapper.map(payMerchant, PayMerchantInfo.class);
        payMerchantInfo.setNotifyUrl(payApi.getNotifyUrl());
        payMerchantInfo.setReturnUrl(payApi.getReturnUrl());

        return payMerchantInfo;
    }
}
