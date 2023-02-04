package com.withdraw.core.handler;

import com.withdraw.core.event.Event;
import com.withdraw.core.event.model.DepositEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DepositHandler extends EventHandlerBase{
    @Override
    public void handler(Event event) {
        if(event instanceof DepositEvent){
            DepositEvent depositEvent = (DepositEvent) event;
            Long recordId = depositEvent.getRecordId();

            ((DepositEvent) event).getTaskIdList().remove(recordId);
        }
    }
}
