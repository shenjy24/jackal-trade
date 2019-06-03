package com.shenjy.entity.pay;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.shenjy.enums.pay.PayApiEnum;
import com.shenjy.enums.pay.PayStatusEnum;
import com.shenjy.enums.pay.PayWayEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 支付回调通知表
 * </p>
 *
 * @author shenjy
 * @since 2019-06-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PayNotify implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 支付流水id
     */
    @TableId("pay_flow_id")
    private Long payFlowId;

    /**
     * 第三方订单id
     */
    @TableField("out_trade_id")
    private String outTradeId;

    /**
     * 第三方支付帐号
     */
    @TableField("out_account")
    private String outAccount;

    /**
     * 支付状态 1.未支付 2.支付中 3.支付成功 4.支付失败
     */
    @TableField("pay_status")
    private PayStatusEnum payStatus;

    /**
     * 支付金额，单位：元
     */
    @TableField("pay_price")
    private BigDecimal payPrice;

    /**
     * 支付方式
     */
    @TableField("pay_way")
    private PayWayEnum payWay;

    /**
     * 支付实现
     */
    @TableField("pay_api_id")
    private PayApiEnum payApiId;

    /**
     * 商户ID,关联表pay_merchant.merchant_id
     */
    @TableField("merchant_id")
    private Long merchantId;

    /**
     * 创建时间
     */
    @TableField("ctime")
    private Long ctime;

    /**
     * 更新时间
     */
    @TableField("utime")
    private Long utime;


}
