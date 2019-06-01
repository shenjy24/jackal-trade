package com.shenjy.mapper.order;

import com.shenjy.entity.order.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author shenjy
 * @since 2019-05-10
 */
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 获取用户订单
     *
     * @param userId
     * @return
     */
    List<Order> listOrder(@Param("userId") Long userId);
}
