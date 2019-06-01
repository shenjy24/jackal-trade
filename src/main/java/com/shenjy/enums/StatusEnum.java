package com.shenjy.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * 【 enter the class description 】
 *
 * @author shenjy 2019/06/01
 */
public enum  StatusEnum implements IEnum<Integer> {
    NORMAL(1, "正常"),
    FORBIDDEN(2, "被禁用")
    ;

    private Integer code;
    private String message;

    StatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
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
