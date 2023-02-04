package com.withdraw.core.handler;

import com.withdraw.core.event.Event;

public abstract class EventHandlerBase {
    public abstract void handler(Event event);
}
