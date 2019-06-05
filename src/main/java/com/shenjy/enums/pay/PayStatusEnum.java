package com.shenjy.enums.pay;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * 支付状态枚举
 *
 * @author
 */
public enum PayStatusEnum implements IEnum<Integer> {
    UN_PAY(1, "待付款"),
    PAY_ING(2, "付款中"),
    SUCCESS(3, "付款成功"),
    FAILURE(4, "付款失败");

    private Integer code;
    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static PayStatusEnum getEnum(Integer code) {
        for (PayStatusEnum paymentStatusTmp : PayStatusEnum.values()) {
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


