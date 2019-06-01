package com.shenjy.dto.pay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 支付通知信息
 *
 * @author shenjy 2019-03-13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayNotifyInfo {

    /**
     * 用户uid
     */
    private Long uid;

    /**
     * 支付流水号
     */
    private Long flowId;

    /**
     * 第三方支付流水号
     */
    private String outTradeId;

    /**
     * 回调金额
     */
    private BigDecimal payPrice;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 支付成功与否
     */
    private Boolean paySuccess;
}
