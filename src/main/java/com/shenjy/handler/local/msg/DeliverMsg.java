package com.shenjy.handler.local.msg;

import com.jonas.service.localevent.LocalMsg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 【 enter the class description 】
 *
 * @author shenjy 2019/06/03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliverMsg implements LocalMsg {
    private Long orderId;
}
