package com.shenjy.enums.pay;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * 【 支付类型枚举 】
 *
 * @author shenjy 2019/06/01
 */
public enum PayWayEnum implements IEnum<Integer> {
    GF_ALIPAY(10, "官方支付宝"),
    GF_WEPAY(20, "官方微信支付")
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

    @Override
    public Integer getValue() {
        return this.code;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
