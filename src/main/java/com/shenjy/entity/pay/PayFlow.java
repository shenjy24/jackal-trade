package com.shenjy.entity.pay;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.FieldFill;
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
 * 支付流水表
 * </p>
 *
 * @author shenjy
 * @since 2019-06-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PayFlow implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 支付流水id
     */
    @TableId("pay_flow_id")
    private Long payFlowId;

    /**
     * 交易订单id
     */
    @TableField("order_id")
    private Long orderId;

    /**
     * 用户id
     */
    @TableField("uid")
    private Long uid;

    /**
     * 第三方订单id (支付宝、微信等)
     */
    @TableField("out_trade_id")
    private String outTradeId;

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

    /** 创建时间 */
    @TableField(value = "ctime", fill = FieldFill.INSERT)
    private Long ctime;

    /** 更新时间 */
    @TableField(value = "utime", fill = FieldFill.INSERT_UPDATE)
    private Long utime;


}
