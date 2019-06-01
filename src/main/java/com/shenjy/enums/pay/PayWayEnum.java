package com.shenjy.enums.pay;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * 【 支付类型枚举 】
 *
 * @author shenjy 2019/06/01
 */
public enum PayWayEnum implements IEnum<Long> {
    GF_ALIPAY(10L, "官方支付宝"),
    GF_WEPAY(20L, "官方微信支付")
    ;

    private Long code;
    private String message;

    PayWayEnum(Long code, String message) {
        this.code = code;
        this.message = message;
    }

    public Long getCode() {
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

    @Override
    public Long getValue() {
        return this.code;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
