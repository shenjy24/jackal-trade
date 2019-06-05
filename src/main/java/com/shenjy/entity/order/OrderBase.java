package com.shenjy.entity.order;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.shenjy.enums.order.DeliverStatusEnum;
import com.shenjy.enums.order.OrderStatusEnum;
import com.shenjy.enums.order.OrderTypeEnum;
import com.shenjy.enums.pay.PayApiEnum;
import com.shenjy.enums.pay.PayWayEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 基础订单表
 * </p>
 *
 * @author shenjy
 * @since 2019-06-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrderBase implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 订单ID
     */
    @TableId("order_id")
    private Long orderId;

    /**
     * 用户ID
     */
    @TableField("uid")
    private Long uid;

    /**
     * 订单价格，单位：元
     */
    @TableField("order_price")
    private BigDecimal orderPrice;

    /** 流水来源单号 */
    @TableField("source_trade_id")
    private String sourceTradeId;

    /**
     * 支付价格，单位：元
     */
    @TableField("pay_price")
    private BigDecimal payPrice;

    /**
     * 订单类型
     */
    @TableField("order_type")
    private OrderTypeEnum orderType;

    /**
     * 订单状态 1.待付款 2.付款中 3.交易成功 4.交易失败
     */
    @TableField("order_status")
    private OrderStatusEnum orderStatus;

    /**
     * 发货状态 1.待发货 2.发货中 3.发货成功 4.发货失败
     */
    @TableField("deliver_status")
    private DeliverStatusEnum deliverStatus;

    /**
     * 支付实现ID
     */
    @TableField("pay_api_id")
    private PayApiEnum payApiId;

    /**
     * 支付类型
     */
    @TableField("pay_way")
    private PayWayEnum payWay;

    /**
     * pay_merchant主键ID
     */
    @TableField("merchant_id")
    private Long merchantId;

    /**
     * 支付完成时间
     */
    @TableField("pay_time")
    private Long payTime;

    /** 创建时间 */
    @TableField(value = "ctime", fill = FieldFill.INSERT)
    private Long ctime;

    /** 更新时间 */
    @TableField(value = "utime", fill = FieldFill.INSERT_UPDATE)
    private Long utime;

}
