package com.haulmont.masquerade.sys;

public interface ProxyFactory {
    Object createProxy(Class interfaceClass, Object instance);
}