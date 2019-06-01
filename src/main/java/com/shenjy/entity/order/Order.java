package com.shenjy.entity.order;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author shenjy
 * @since 2019-05-10
 */
@Data
@TableName("order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("order_id")
    private Long orderId;

    @TableField("user_id")
    private Long userId;

    @TableField(value = "ctime", fill = FieldFill.INSERT)
    private Long ctime;

    @TableField(value = "utime", fill = FieldFill.INSERT_UPDATE)
    private Long utime;

}
