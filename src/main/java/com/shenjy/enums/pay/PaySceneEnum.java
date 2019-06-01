package com.shenjy.enums.pay;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * 支付场景枚举
 *
 * @author shenjy 2019/06/01
 */
public enum PaySceneEnum implements IEnum<Long> {

    ;

    private Long code;
    private String message;

    PaySceneEnum(Long code, String message) {
        this.code = code;
        this.message = message;
    }

    public Long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static PaySceneEnum getEnum(Long code) {
        if (code == null) {
            return null;
        }
        for (PaySceneEnum payWayEnum : PaySceneEnum.values()) {
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
