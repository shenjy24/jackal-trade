package com.shenjy.service.pay.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shenjy.entity.pay.PayApi;
import com.shenjy.enums.StatusEnum;
import com.shenjy.mapper.pay.PayApiMapper;
import com.shenjy.service.pay.PayApiService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付实现信息表 服务实现类
 * </p>
 *
 * @author shenjy
 * @since 2019-06-01
 */
@Service
public class PayApiServiceImpl extends ServiceImpl<PayApiMapper, PayApi> implements PayApiService {

    @Override
    public PayApi getPayApiById(Long payApiId) {
        if (null == payApiId) {
            return null;
        }

        QueryWrapper<PayApi> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(PayApi::getPayApiId, payApiId);
        wrapper.lambda().eq(PayApi::getUseStatus, StatusEnum.NORMAL.getCode());

        return baseMapper.selectOne(wrapper);
    }
}
