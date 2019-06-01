package com.shenjy.enums.pay;

/**
 * 支付方法枚举
 * code为两位数，第一位表示payWay，第二位表示序号
 * 1.支付宝
 * 2.微信
 *
 * @author shenjy 2019/06/01
 */
public enum PayApiEnum {
    GF_ALIPAY_PAGE(10L, PayWayEnum.GF_ALIPAY, "官方支付宝电脑支付"),

    ;

    private Long code;
    private PayWayEnum payWay;
    private String message;

    PayApiEnum(Long code, PayWayEnum payWay, String message) {
        this.code = code;
        this.payWay = payWay;
        this.message = message;
    }

    public Long getCode() {
        return code;
    }

    public PayWayEnum getPayWay() {
        return payWay;
    }

    public String getMessage() {
        return message;
    }

    public static PayApiEnum getEnum(Long code) {
        if (code == null) {
            return null;
        }
        for (PayApiEnum payWayEnum : PayApiEnum.values()) {
            if (payWayEnum.getCode().equals(code)) {
                return payWayEnum;
            }
        }
        return null;
    }
}
