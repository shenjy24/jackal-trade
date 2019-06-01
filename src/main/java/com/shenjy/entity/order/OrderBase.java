package com.shenjy.entity.order;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
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

    /**
     * 支付价格，单位：元
     */
    @TableField("pay_price")
    private BigDecimal payPrice;

    /**
     * 订单类型
     */
    @TableField("order_type")
    private Integer orderType;

    /**
     * 订单状态 1.待付款 2.已付款，待处理 3.交易成功 4.交易失败
     */
    @TableField("order_status")
    private Integer orderStatus;

    /**
     * 发货状态 1.待发货 2.发货中 3.已发货 4.发货失败
     */
    @TableField("deliver_status")
    private Integer deliverStatus;

    /**
     * 支付实现ID
     */
    @TableField("pay_api_id")
    private Long payApiId;

    /**
     * 支付类型
     */
    @TableField("pay_way")
    private Integer payWay;

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
