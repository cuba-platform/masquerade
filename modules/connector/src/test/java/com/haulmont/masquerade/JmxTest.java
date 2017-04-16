package com.haulmont.masquerade;

import com.haulmont.masquerade.jmx.ConfigStorage;
import org.junit.Test;

import static com.haulmont.masquerade.Connector.jmx;
import static org.junit.Assert.assertNotNull;

public class JmxTest {
    @Test
    public void bridge() {
        ConfigStorage configStorage = jmx(ConfigStorage.class);
        assertNotNull(configStorage);

        String appProperties = configStorage.printAppProperties();
        assertNotNull(appProperties);
    }
}