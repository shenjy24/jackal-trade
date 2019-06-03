package com.shenjy.service.pay;

import com.shenjy.enums.pay.PayApiEnum;

import java.util.Map;

/**
 * 【 enter the class description 】
 *
 * @author shenjy 2019/06/03
 */
public interface PayManageService {

    boolean handlePayNotify(Map<String, String> payNotifyMap, PayApiEnum payApiEnum);
}
