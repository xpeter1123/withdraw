package com.withdraw.core.event;

import com.withdraw.core.handler.EventHandlerBase;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class EventListener implements Runnable {

    protected Event event;
    protected static final Map<Integer, EventHandlerBase> mapEventHandler = new HashMap<>();

    static {

    }

    public EventListener(Event event) {
        this.event = event;
    }

    @Override
    public void run() {
        EventHandlerBase handler = mapEventHandler.get(event.getEventType().ordinal());

        log.debug("process event type ={}", event.getEventType().ordinal());
        if (handler != null) {
            log.info("process event ={}", event.getEventId() + " by " + handler);
            try {
                handler.handler(event);
            } catch (Exception ex) {
                log.error("error {}", ExceptionUtils.getStackTrace(ex));
            }
        }
    }

    public Event getEvent() {
        return event;
    }
}
