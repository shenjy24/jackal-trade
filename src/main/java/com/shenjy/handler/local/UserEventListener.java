package com.shenjy.handler.local;

import com.shenjy.handler.local.msg.UserMsg;
import com.jonas.service.localevent.LocalEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 【 enter the class description 】
 *
 * @author shenjy 2019/05/04
 */
@Component
public class UserEventListener {

    @Async
    @EventListener(condition = "#event.topic==T(com.shenjy.handler.local.LocalTopic).TOPIC_USER")
    public void onListen(LocalEvent event) {
        UserMsg msg = (UserMsg) event.getContent();
        System.out.println(msg);
    }
}
