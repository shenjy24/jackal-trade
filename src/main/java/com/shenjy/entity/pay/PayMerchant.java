package com.shenjy.entity.pay;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 【 支付商户信息 】
 *
 * @author shenjy 2019/06/01
 */
@Data
@TableName("pay_merchant")
public class PayMerchant implements Serializable {

    /** 主键ID */
    @TableId("merchant_id")
    private Long merchantId;

    /** 商户名称 */
    @TableField("merchant_name")
    private String merchantName;

    /** 商户ID */
    @TableField("partner_id")
    private String partnerId;

    /** 第三方支付分配的应用ID */
    @TableField("app_id")
    private String appId;

    /** 商户状态：1.正常 2.被封 */
    @TableField("merchant_status")
    private Integer merchantStatus;

    /** 支付类型 */
    @TableField("pay_way")
    private Integer payWay;

    /** 签名方式：rsa或rsa2 目前只用于支付宝 */
    @TableField("sign_type")
    private String signType;

    /** 支付私钥 */
    @TableField("private_key")
    private String privateKey;

    /** 支付公钥 */
    @TableField("public_key")
    private String publicKey;

    /** 创建时间 */
    @TableField("ctime")
    private Long ctime;

    /** 更新时间 */
    @TableField("utime")
    private Long utime;
}
