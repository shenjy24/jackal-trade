package com.shenjy.common;

import com.jonas.common.CodeStatus;
import lombok.Data;

/**
 * 【操作结果】
 *
 * @author yangjh  26/04/2017.
 */
@Data
public class OperationResult<T> {

    private Boolean succ = true;

    private String code;

    private String message;

    private T entity;

    public OperationResult(T entity) {
        this.entity = entity;
    }

    public OperationResult(CodeStatus errorCode) {
        this.succ = false;
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public OperationResult(String code, String message) {
        this.succ = false;
        this.code = code;
        this.message = message;
    }
}
