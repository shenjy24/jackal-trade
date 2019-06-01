package com.shenjy.entity.pay;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shenjy.enums.SystemTypeEnum;
import com.shenjy.enums.pay.PayApiEnum;
import com.shenjy.enums.pay.PaySceneEnum;
import com.shenjy.enums.pay.PayWayEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 【 支付方式 】
 *
 * @author shenjy 2019/06/01
 */
@Data
@TableName("pay_api_mapping")
public class PayApiMapping implements Serializable {

    /** 主键ID */
    @TableId("mapping_id")
    private Long mappingId;

    /** 支付实现ID */
    @TableField("pay_api_id")
    private PayApiEnum payApiId;

    /** 支付类型 */
    @TableField("pay_way")
    private PayWayEnum payWay;

    /** 支付场景 */
    @TableField("pay_scene")
    private PaySceneEnum payScene;

    /** 系统类型：1.iOS 2.Android 3.PC */
    @TableField("system_type")
    private SystemTypeEnum systemType;

    /** 页面排序 */
    @TableField("sort")
    private Integer sort;

    /** 备注说明 */
    @TableField("remark")
    private String remark;

    /** 创建时间 */
    @TableField("ctime")
    private Long ctime;

    /** 修改时间 */
    @TableField("utime")
    private Long utime;
}
