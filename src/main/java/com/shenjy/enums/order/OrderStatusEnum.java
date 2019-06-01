package com.shenjy.enums.order;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * 订单状态枚举
 *
 * @author shenjy
 */
public enum OrderStatusEnum implements IEnum<Integer> {
    UN_PAY(1, "待付款"),
    PAY_ING(2, "已付款，待处理"),
    SUCCESS(3, "交易成功"),
    FAILURE(4, "交易失败");

    private Integer code;
    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static OrderStatusEnum getEnum(Integer code) {
        for (OrderStatusEnum paymentStatusTmp : OrderStatusEnum.values()) {
            if (paymentStatusTmp.getCode().equals(code)) {
                return paymentStatusTmp;
            }
        }
        return null;
    }

    @Override
    public Integer getValue() {
        return this.code;
    }

    @Override
    public String toString() {
        return this.message;
    }
}


