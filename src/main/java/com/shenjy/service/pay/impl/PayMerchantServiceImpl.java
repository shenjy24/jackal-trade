package com.shenjy.service.pay.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shenjy.entity.pay.PayMerchant;
import com.shenjy.enums.StatusEnum;
import com.shenjy.mapper.pay.PayMerchantMapper;
import com.shenjy.service.pay.PayMerchantService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付商户信息表 服务实现类
 * </p>
 *
 * @author shenjy
 * @since 2019-06-01
 */
@Service
public class PayMerchantServiceImpl extends ServiceImpl<PayMerchantMapper, PayMerchant> implements PayMerchantService {

    @Override
    public PayMerchant getMerchantById(Long merchantId) {
        if (null == merchantId) {
            return null;
        }

        QueryWrapper<PayMerchant> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(PayMerchant::getMerchantId, merchantId);
        wrapper.lambda().eq(PayMerchant::getMerchantStatus, StatusEnum.NORMAL.getCode());

        return baseMapper.selectOne(wrapper);
    }
}
