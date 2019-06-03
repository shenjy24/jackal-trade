package com.shenjy.service.payapi.alipay;

import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.google.common.collect.Maps;
import com.shenjy.dto.pay.PayMerchantInfo;
import com.shenjy.enums.pay.PayApiEnum;
import com.shenjy.service.payapi.AbstractAliPayService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Map;

/**
 * 支付宝PC网站支付实现
 *
 * @author shenjy.
 */
@Service(value = "alipayPage")
public class AliPayPageService extends AbstractAliPayService {

    @Override
    protected Long getPayApiId() {
        return PayApiEnum.GF_ALIPAY_PAGE.getCode();
    }

    @Override
    public Map<String, Object> createPayMetaData(Long flowId, BigDecimal payPrice, String goodsDesc, String userIp) {
        Map<String, Object> metaData = Maps.newHashMap();
        PayMerchantInfo payMerchant = getCurrentPayMerchant();
        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient(gateway, payMerchant.getAppId(),
                payMerchant.getPrivateKey(), "json", AlipayConstants.CHARSET_UTF8,
                payMerchant.getPublicKey(), payMerchant.getSignType());
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.wap.pay
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        model.setBody(goodsDesc);
        model.setSubject(goodsDesc);
        model.setOutTradeNo(flowId.toString());
        model.setTimeoutExpress("15m");
        DecimalFormat df = new DecimalFormat("#0.00");
        model.setTotalAmount(df.format(payPrice));
        model.setProductCode("FAST_INSTANT_TRADE_PAY");
        request.setBizModel(model);
        //接口回调
        request.setNotifyUrl(payMerchant.getNotifyUrl());
        //页面回调
        request.setReturnUrl(payMerchant.getReturnUrl() + flowId);

        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradePagePayResponse response = alipayClient.sdkExecute(request);
            metaData.put("body", gateway + "?" + response.getBody());
        } catch (Exception e) {
        }
        return metaData;
    }
}
