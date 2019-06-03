package com.shenjy.controller;

import com.shenjy.enums.pay.PayApiEnum;
import com.shenjy.service.pay.PayManageService;
import com.shenjy.util.net.RequestUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 【 enter the class description 】
 *
 * @author shenjy 2019/06/03
 */
@RestController
@RequestMapping("/pay/notify")
public class PayNotifyController {

    @Autowired
    private PayManageService payManageService;

    /**
     * 支付宝电脑支付
     *
     * @param request
     * @return
     */
    @PostMapping("/alipayPage")
    public String alipayPage(HttpServletRequest request) {
        Map<String, String> payNotifyMap = RequestUtils.getParamByRequest(request);
        if (MapUtils.isEmpty(payNotifyMap)) {
            return "fail";
        }

        boolean result = payManageService.handlePayNotify(payNotifyMap, PayApiEnum.GF_ALIPAY_PAGE);
        return result ? "success" : "fail";
    }
}
