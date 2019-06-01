package com.shenjy.enums.pay;

/**
 * 支付状态枚举
 *
 * @author
 */
public enum PayStatusEnum {
    UN_PAY(1, "待付款"),
    PAY_ING(2, "付款中"),
    PAY_SUCCESS(3, "付款成功"),
    PAY_FAILURE(4, "付款失败");

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

}


