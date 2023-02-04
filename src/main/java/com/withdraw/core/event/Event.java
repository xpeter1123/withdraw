package com.withdraw.core.event;

import com.withdraw.utils.Utils;

public class Event {

    protected EventType eventType;
    protected long eventId;

    public Event(EventType eventType) {
        this.eventType = eventType;
        this.eventId = Utils.makeId();
    }

    public EventType getEventType() {
        return eventType;
    }

    public long getEventId() {
        return eventId;
    }

}