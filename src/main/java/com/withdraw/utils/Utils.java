package com.withdraw.utils;

import java.util.concurrent.atomic.AtomicLong;

public class Utils {
    public final static AtomicLong id = new AtomicLong(0);
    public static long makeId() {
        return id.getAndIncrement();
    }
}
