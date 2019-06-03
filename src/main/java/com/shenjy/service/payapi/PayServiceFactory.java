package com.shenjy.service.payapi;

import com.shenjy.enums.pay.PayApiEnum;
import com.shenjy.service.payapi.alipay.AliPayPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 支付服务工厂方法
 *
 * @author shenjy 2019/06/01
 */
@Service
public class PayServiceFactory {

    @Autowired
    @Qualifier("alipayPage")
    private AliPayPageService aliPayPageService;

    public BasePayService getService(PayApiEnum payApi) {
        BasePayService payService;
        switch (payApi) {
            case GF_ALIPAY_PAGE:
                payService = this.aliPayPageService;
                break;
            default:
                throw new RuntimeException("未匹配到合适的支付方式");
        }

        return payService;
    }
}
