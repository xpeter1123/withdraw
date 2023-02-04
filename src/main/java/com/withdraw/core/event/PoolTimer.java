package com.withdraw.core.event;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PoolTimer implements CommonTimer {
    /* Manage timeout for task in pool */
    private final ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

    public PoolTimer(int corePoolSize, int maximumPoolSize) {
        scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(corePoolSize);
        scheduledThreadPoolExecutor.setMaximumPoolSize(maximumPoolSize);
    }

    @Override
    public void executeTask(Runnable task, long timing, TimeUnit unit) {
        new PoolTimeout(scheduledThreadPoolExecutor.schedule(task, timing, unit));
    }

    @Override
    public void stopTimer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
