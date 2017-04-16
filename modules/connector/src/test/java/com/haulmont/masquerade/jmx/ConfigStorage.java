package com.haulmont.masquerade.jmx;

@JmxName("app-core.cuba:type=ConfigStorage")
public interface ConfigStorage {
    String printAppProperties();
}