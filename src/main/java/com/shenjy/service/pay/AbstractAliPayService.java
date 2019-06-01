package com.shenjy.service.pay;

import com.alipay.api.AlipayConstants;
import com.alipay.api.internal.util.AlipaySignature;
import com.shenjy.common.OperationResult;
import com.shenjy.dto.pay.PayMerchantInfo;
import com.shenjy.dto.pay.PayNotifyInfo;
import com.shenjy.entity.pay.PayFlow;
import com.shenjy.enums.pay.PayStatusEnum;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.util.Map;

import static com.jonas.common.SystemCode.PARAM_ERROR;
import static com.shenjy.common.BizErrorCode.*;

/**
 * 支付包抽象基类
 * 1、支付回调校验
 *
 * @author shenjy 2019/06/01
 */
public abstract class AbstractAliPayService extends AbstractPayService {

    /** 支付宝支付网关 */
    @Value("trade.alipay.gateway")
    private String gateway;

    /**
     * 交易创建，等待买家付款
     */
    protected static final String WAIT_BUYER_PAY = "WAIT_BUYER_PAY";

    /**
     * 交易支付成功
     */
    protected static final String TRADE_SUCCESS = "TRADE_SUCCESS";

    /**
     * 交易结束，不可退款
     */
    protected static final String TRADE_FINISHED = "TRADE_FINISHED";

    @Override
    public OperationResult<PayNotifyInfo> validatePayNotify(Map<String, String> payNotifyMap) {
        if (MapUtils.isEmpty(payNotifyMap)) {
            return new OperationResult<>(PARAM_ERROR);
        }

        Long flowId = Long.parseLong(payNotifyMap.get("out_trade_no"));
        String outTradeId = payNotifyMap.get("trade_no");

        //1.验签
        //1.1判断流水是否存在
        PayFlow payFlow = payFlowService.getById(flowId);
        if (null == payFlow) {
            return new OperationResult<>(PAY_FLOW_NOT_EXIST);
        }

        //1.2验签
        PayMerchantInfo merchant = getCurrentPayMerchant();
        try {
            boolean isValid = AlipaySignature.rsaCheckV1(payNotifyMap, merchant.getPublicKey(), AlipayConstants.CHARSET_UTF8, merchant.getSignType());
            if (!isValid) {
                return new OperationResult<>(PAY_SIGN_ERROR);
            }
        } catch (Exception e) {
            return new OperationResult<>(PAY_SIGN_ERROR);
        }

        //2.参数校验
        //2.1 金额是否一致
        BigDecimal payPrice = new BigDecimal(payNotifyMap.get("total_amount"));
        if (payPrice.compareTo(payFlow.getPayPrice()) != 0) {
            return new OperationResult<>(PAY_PRICE_ERROR);
        }

        //2.2 判断充值记录状态
        if (!PayStatusEnum.UN_PAY.getCode().equals(payFlow.getPayStatus())) {
            return new OperationResult(PAY_FLOW_HAS_HANDLE);
        }

        //2.3 卖家支付宝用户号是否正确
        String sellerId = payNotifyMap.get("sellerId");
        if (StringUtils.isNotBlank(sellerId) && !sellerId.equals(merchant.getPartnerId())) {
            return new OperationResult(PAY_SELLER_ERROR);
        }

        //2.4 app_id是否正确
        String appId = payNotifyMap.get("app_id");
        if (!merchant.getAppId().equals(appId)) {
            return new OperationResult(PAY_APPID_ERROR);
        }

        // 2.5 交易结果
        String tradeStatus = payNotifyMap.get("trade_status");
        if (WAIT_BUYER_PAY.equals(tradeStatus)) {
            return new OperationResult(PAY_WAIT_BUYER_PAY);
        }
        //只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功
        Boolean paySuccess = false;
        if (TRADE_SUCCESS.equals(tradeStatus) || TRADE_FINISHED.equals(tradeStatus)) {
            paySuccess = true;
        }

        // 买家支付宝用户号
        String userAccount = payNotifyMap.get("buyer_id");
        return new OperationResult(new PayNotifyInfo(payFlow.getUid(), flowId, outTradeId, payPrice, userAccount, paySuccess));
    }
}
