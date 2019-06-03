package com.shenjy.service.pay.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.shenjy.entity.pay.PayFlow;
import com.shenjy.enums.pay.PayStatusEnum;
import com.shenjy.mapper.pay.PayFlowMapper;
import com.shenjy.service.pay.PayFlowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付流水表 服务实现类
 * </p>
 *
 * @author shenjy
 * @since 2019-06-01
 */
@Service
public class PayFlowServiceImpl extends ServiceImpl<PayFlowMapper, PayFlow> implements PayFlowService {

    @Override
    public boolean updatePayFlow(Long flowId, String outTradeId, PayStatusEnum oldStatus, PayStatusEnum newStatus) {
        UpdateWrapper<PayFlow> wrapper = new UpdateWrapper<>();
        wrapper.lambda().eq(PayFlow::getPayStatus, oldStatus);
        wrapper.lambda().eq(PayFlow::getPayFlowId, flowId);

        wrapper.lambda().set(PayFlow::getPayStatus, newStatus);
        wrapper.lambda().set(PayFlow::getOutTradeId, outTradeId);

        return this.update(wrapper);
    }
}
