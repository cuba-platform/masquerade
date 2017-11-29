package com.haulmont.masquerade.util;

import com.haulmont.masquerade.components.Component;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation for methods of {@link Component} that should be logged.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
}