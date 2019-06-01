package com.shenjy.dto.pay;

import lombok.Data;

/**
 * 【 enter the class description 】
 *
 * @author shenjy 2019/06/01
 */
@Data
public class PayMerchantInfo {
    /** pay_merchant表主键 */
    private Long merchantId;

    /** 商户ID */
    private String partnerId;

    /** 第三方支付分配的应用ID */
    private String appId;

    /** 支付类型 */
    private Integer payWay;

    /** 签名方式：rsa或rsa2 目前只用于支付宝 */
    private String signType;

    /** 支付私钥 */
    private String privateKey;

    /** 支付公钥 */
    private String publicKey;

    /** 支付回调地址 */
    private String notifyUrl;
}
