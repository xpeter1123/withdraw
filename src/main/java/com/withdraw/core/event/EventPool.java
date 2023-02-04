package com.withdraw.core.event;

import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

public interface EventPool {
    Future postEvent(Event event) throws RejectedExecutionException;
}
