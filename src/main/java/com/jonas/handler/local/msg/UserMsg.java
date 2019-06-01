package com.jonas.handler.local.msg;

import com.jonas.service.localevent.LocalMsg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 【 enter the class description 】
 *
 * @author shenjy 2019/05/04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMsg implements LocalMsg {
    private String userName;
}
