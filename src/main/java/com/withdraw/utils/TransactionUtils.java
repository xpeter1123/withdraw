package com.withdraw.utils;

import java.util.concurrent.atomic.AtomicLong;

public class TransactionUtils {
    static final AtomicLong counter = new AtomicLong(System.currentTimeMillis() * 1000);

    public static Long createTransactionId() {
        return counter.get();
    }
}
