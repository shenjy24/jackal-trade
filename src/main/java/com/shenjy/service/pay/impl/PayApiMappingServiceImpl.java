package com.shenjy.service.pay.impl;

import com.shenjy.entity.pay.PayApiMapping;
import com.shenjy.mapper.pay.PayApiMappingMapper;
import com.shenjy.service.pay.PayApiMappingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付方式与支付场景映射表 服务实现类
 * </p>
 *
 * @author shenjy
 * @since 2019-06-01
 */
@Service
public class PayApiMappingServiceImpl extends ServiceImpl<PayApiMappingMapper, PayApiMapping> implements PayApiMappingService {

}
