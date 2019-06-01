package com.shenjy.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * 系统枚举
 *
 * @author shenjy 2019/06/01
 */
public enum SystemTypeEnum implements IEnum<Integer> {
    IOS(1, "iOS"),
    ANDROID(2, "Android"),
    PC(3, "PC")
    ;

    SystemTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;

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
