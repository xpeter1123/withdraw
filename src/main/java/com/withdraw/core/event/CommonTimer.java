package com.withdraw.core.event;

import java.util.concurrent.TimeUnit;

public interface CommonTimer {
    void executeTask(Runnable task, long timing, TimeUnit unit);

    void stopTimer();
}
