package com.shenjy.enums.pay;

/**
 * 【 支付类型枚举 】
 *
 * @author shenjy 2019/06/01
 */
public enum PayWayEnum {
    GF_ALIPAY(1, "官方支付宝"),
    GF_WEPAY(2, "官方微信支付")
    ;

    private Integer code;
    private String message;

    PayWayEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static PayWayEnum getEnum(Long code) {
        if (code == null) {
            return null;
        }
        for (PayWayEnum payWayEnum : PayWayEnum.values()) {
            if (payWayEnum.getCode().equals(code)) {
                return payWayEnum;
            }
        }
        return null;
    }
}
