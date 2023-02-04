package com.withdraw.utils;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Optional;

@Slf4j
public final class Methods {
    private Methods() {}

    public static Optional<Object> invoke(String name, Object target) {
        try {
            PropertyDescriptor descriptor = new PropertyDescriptor(name, target.getClass());
            Method getter = descriptor.getReadMethod();
            return Optional.ofNullable(getter.invoke(target));
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[err] Invoke method failed", e);
            }
        }
        return Optional.empty();
    }

    @SuppressWarnings("unchecked")
    public static <T> Optional<T> invoke(String name, Object target, Class<T> cls) {
        try {
            PropertyDescriptor descriptor = new PropertyDescriptor(name,
                    target.getClass());
            Method getter = descriptor.getReadMethod();
            Object object = getter.invoke(target);
            if (object != null && cls == object.getClass()) {
                return Optional.of((T) object);
            }
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[err] Invoke method failed", e);
            }
        }
        return Optional.empty();
    }
}