package com.shenjy.service.pay;

import com.shenjy.entity.pay.PayApi;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 支付实现信息表 服务类
 * </p>
 *
 * @author shenjy
 * @since 2019-06-01
 */
public interface PayApiService extends IService<PayApi> {

    PayApi getPayApiById(Long payApiId);
}
