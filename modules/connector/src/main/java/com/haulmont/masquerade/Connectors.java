package com.haulmont.masquerade;

import com.haulmont.masquerade.jmx.JmxCallHandler;
import com.haulmont.masquerade.jmx.JmxName;

import java.lang.reflect.Proxy;

public class Connectors {
    public static <T> T jmx(Class<T> clazz) {
        return jmx(new JmxHost(null, null, ":7777"), clazz);
    }

    @SuppressWarnings("unchecked")
    public static <T> T jmx(JmxHost hostInfo, Class<T> clazz) {
        JmxName jmxName = clazz.getAnnotation(JmxName.class);
        if (jmxName == null) {
            throw new RuntimeException("There is no @JmxName annotation for " + clazz);
        }
        if ("".equals(jmxName.value())) {
            throw new RuntimeException("JmxName.value is empty for " + clazz);
        }

        return (T) Proxy.newProxyInstance(Connectors.class.getClassLoader(), new Class[]{clazz},
                new JmxCallHandler(hostInfo, jmxName.value()));
    }

    public static class JmxHost {
        private String address;
        private String user;
        private String password;

        public JmxHost(String user, String password, String address) {
            this.user = user;
            this.password = password;
            this.address = address;
        }

        public String getAddress() {
            return address;
        }

        public String getUser() {
            return user;
        }

        public String getPassword() {
            return password;
        }
    }
}