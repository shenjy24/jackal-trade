package com.shenjy.enums.order;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * 发货状态枚举
 *
 * @author shenjy
 */
public enum DeliverStatusEnum implements IEnum<Integer> {
    UN_DELIVER(1, "待发货"),
    DELIVER_ING(2, "发货中"),
    SUCCESS(3, "发货成功"),
    FAILURE(4, "发货失败");

    private Integer code;
    private String message;

    DeliverStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static DeliverStatusEnum getEnum(Integer code) {
        for (DeliverStatusEnum paymentStatusTmp : DeliverStatusEnum.values()) {
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


