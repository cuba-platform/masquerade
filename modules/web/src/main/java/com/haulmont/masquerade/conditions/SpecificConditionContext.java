package com.haulmont.masquerade.conditions;

import java.util.function.Supplier;

public class SpecificConditionContext {

    private static final ThreadLocal<SpecificConditionHandler> holder = new ThreadLocal<>();

    public static void with(SpecificConditionHandler handler, Runnable r) {
        holder.set(handler);
        try {
            r.run();
        } finally {
            holder.set(null);
        }
    }

    public static <T> T get(SpecificConditionHandler handler, Supplier<T> supplier) {
        holder.set(handler);
        try {
            return supplier.get();
        } finally {
            holder.set(null);
        }
    }

    public static SpecificConditionHandler getHandler() {
        return holder.get();
    }
}