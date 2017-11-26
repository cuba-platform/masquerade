package com.haulmont.masquerade.util;

public class NotImplementedException extends RuntimeException {
    public NotImplementedException() {
        super("This UI component still in development");
    }
}