package com.shenjy.entity.pay;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shenjy.enums.pay.PayWayEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 【 支付实现方式 】
 *
 * @author shenjy 2019/06/01
 */
@Data
@TableName("pay_api")
public class PayApi implements Serializable {

    /** 支付ID */
    @TableId("pay_api_id")
    private Long payApiId;

    /** 支付名称 */
    @TableField("pay_api_name")
    private String payApiName;

    /** 支付类型 */
    @TableField("pay_way")
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private PayWayEnum payWay;

    /** 商户ID,关联表pay_merchant.merchant_id */
    @TableField("merchant_id")
    private Long merchantId;

    /** 使用状态：1.使用 2.废弃 */
    @TableField("use_status")
    private Integer useStatus;

    /** 支付回调接口地址 */
    @TableField("notify_url")
    private String notifyUrl;

    /** 支付回调页面地址 */
    @TableField("return_url")
    private String returnUrl;

    /** 创建时间 */
    @TableField(value = "ctime", fill = FieldFill.INSERT)
    private Long ctime;

    /** 更新时间 */
    @TableField(value = "utime", fill = FieldFill.INSERT_UPDATE)
    private Long utime;
}
