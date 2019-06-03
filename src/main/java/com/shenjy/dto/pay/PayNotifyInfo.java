package com.shenjy.dto.pay;

import com.shenjy.entity.pay.PayFlow;
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
     * 流水
     */
    private PayFlow payFlow;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 支付成功与否
     */
    private Boolean paySuccess;
}
