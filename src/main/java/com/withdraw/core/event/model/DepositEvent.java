package com.withdraw.core.event.model;

import com.withdraw.core.event.Event;
import com.withdraw.core.event.EventType;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class DepositEvent extends Event {
    private Long recordId;

    Set<Long> taskIdList;

    public DepositEvent(Set<Long> taskIdList) {
        super(EventType.DEPOSIT_HANDLE);
        this.taskIdList = taskIdList;
    }
}