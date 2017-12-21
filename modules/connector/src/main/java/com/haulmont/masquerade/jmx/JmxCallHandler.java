/*
 * Copyright (c) 2008-2017 Haulmont.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.haulmont.masquerade.jmx;

import com.haulmont.masquerade.Connectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

public class JmxCallHandler implements InvocationHandler {
    private static final Logger log = LoggerFactory.getLogger(JmxCallHandler.class);

    private final Connectors.JmxHost hostInfo;
    private final String objectName;

    public JmxCallHandler(Connectors.JmxHost hostInfo, String objectName) {
        this.hostInfo = hostInfo;
        this.objectName = objectName;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        JMXServiceURL url;
        try {
            url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://" + hostInfo.getAddress() + "/jmxrmi");
        } catch (MalformedURLException e) {
            throw new RuntimeException("Incorrect service URL", e);
        }

        Map<String, Object> properties = new HashMap<>();
        if (hostInfo.getUser() != null) {
            properties.put(JMXConnector.CREDENTIALS, new String[]{hostInfo.getUser(), hostInfo.getPassword()});
        }

        try (JMXConnector jmxc = JMXConnectorFactory.connect(url, properties)) {
            MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();

            ObjectName mbeanName;
            try {
                mbeanName = new ObjectName(objectName);
            } catch (MalformedObjectNameException e) {
                throw new RuntimeException("Incorrect JMX object name", e);
            }

            MBeanServerInvocationHandler wrappedHandler = new MBeanServerInvocationHandler(mbsc, mbeanName);

            if (args != null) {
                log.info("Invoke method {} of {} with parameters {}", method.getName(), objectName, args);
            } else {
                log.info("Invoke method {} of {}", method.getName(), objectName);
            }

            return wrappedHandler.invoke(proxy, method, args);
        } catch (IOException e) {
            throw new RuntimeException("Unable to perform JMX call", e);
        }
    }
}