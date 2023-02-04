package com.withdraw.core.process;

import com.withdraw.config.ProcessSetting;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Process {
    /**
     * Process setting key.
     *
     * @see ProcessSetting
     */
    String key();
}
