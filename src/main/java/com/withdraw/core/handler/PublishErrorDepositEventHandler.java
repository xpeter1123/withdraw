package com.withdraw.core.handler;

import com.withdraw.core.event.Event;
import com.withdraw.core.event.model.DepositEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PublishErrorDepositEventHandler extends EventHandlerBase{
    @Override
    public void handler(Event event) {
        if(event instanceof DepositEvent){
           //publish message to message queue
        }
    }
}
