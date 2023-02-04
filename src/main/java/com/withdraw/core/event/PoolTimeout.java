package com.withdraw.core.event;

import java.util.concurrent.Future;

public class PoolTimeout implements CommonTimeout {

    private final Future<?> future;

    public PoolTimeout(Future<?> future) {
        this.future = future;
    }

    @Override
    public boolean cancel() {
        if (future != null) {
            return future.cancel(true);
        }

        return false;
    }
}
