package com.shenjy.enums.order;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * 订单状态枚举
 *
 * @author shenjy
 */
public enum OrderTypeEnum implements IEnum<Integer> {
    ;

    private Integer code;
    private String message;

    OrderTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static OrderTypeEnum getEnum(Integer code) {
        for (OrderTypeEnum paymentStatusTmp : OrderTypeEnum.values()) {
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


