package com.shenjy.service.pay.impl;

import com.shenjy.entity.pay.PayNotify;
import com.shenjy.mapper.pay.PayNotifyMapper;
import com.shenjy.service.pay.PayNotifyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付回调通知表 服务实现类
 * </p>
 *
 * @author shenjy
 * @since 2019-06-03
 */
@Service
public class PayNotifyServiceImpl extends ServiceImpl<PayNotifyMapper, PayNotify> implements PayNotifyService {

}
