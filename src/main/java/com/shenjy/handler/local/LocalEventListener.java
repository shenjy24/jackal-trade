package com.shenjy.handler.local;

import com.jonas.service.localevent.LocalEvent;
import com.shenjy.handler.local.msg.DeliverMsg;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 【 enter the class description 】
 *
 * @author shenjy 2019/05/04
 */
@Component
public class LocalEventListener {

    @Async
    @EventListener(condition = "#event.topic==T(com.shenjy.handler.local.LocalTopic).TOPIC_DELIVER")
    public void onDeliver(LocalEvent event) {
        DeliverMsg msg = (DeliverMsg) event.getContent();

    }
}
