package com.shenjy.service.pay;

import com.shenjy.entity.pay.PayFlow;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shenjy.enums.pay.PayStatusEnum;

/**
 * <p>
 * 支付流水表 服务类
 * </p>
 *
 * @author shenjy
 * @since 2019-06-01
 */
public interface PayFlowService extends IService<PayFlow> {

    boolean updatePayFlow(Long flowId, String outTradeId, PayStatusEnum oldStatus, PayStatusEnum newStatus);
}
