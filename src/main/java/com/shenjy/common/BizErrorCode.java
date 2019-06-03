package com.shenjy.common;

import com.jonas.common.CodeStatus;

/**
 * 【 enter the class description 】
 *
 * @author shenjy 2019/06/01
 */
public enum BizErrorCode implements CodeStatus {

    /** 通用 */
    NOT_REPEAT_PROCESS("400001", "操作已提交，请勿重复操作"),

    /** 订单 */
    ORDER_HAS_EXIST("400101", "订单已存在"),

    /** 支付 */
    PAY_FLOW_NOT_EXIST("400201", "支付流水不存在"),
    PAY_SIGN_ERROR("400202", "支付验签异常"),
    PAY_PRICE_ERROR("400203", "支付金额不一致"),
    PAY_FLOW_HAS_HANDLE("400204", "支付流水已处理"),
    PAY_SELLER_ERROR("400205", "卖家支付帐号异常"),
    PAY_APPID_ERROR("400206", "appId异常"),
    PAY_WAIT_BUYER_PAY("400207", "买家待支付"),
    PAY_API_NOT_EXIST("400208", "支付实现不存在"),
    PAY_MERCHANT_NOT_EXIST("400209", "支付商户不存在")
    ;

    private String code;
    private String message;

    BizErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
