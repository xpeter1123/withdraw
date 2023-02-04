package com.withdraw.core.event.model;

import com.withdraw.core.event.Event;
import com.withdraw.core.event.EventType;
import com.withdraw.domain.entity.DepositInitialEntity;

public class ErrorDepositEvent extends Event {
    public ErrorDepositEvent(DepositInitialEntity depositEntity) {
        super(EventType.DEPOSIT_ERROR_PUBLISH);
    }
}
