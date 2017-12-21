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

package com.haulmont.masquerade;

import com.haulmont.masquerade.jmx.ConfigStorage;
import org.junit.Ignore;
import org.junit.Test;

import static com.haulmont.masquerade.Connectors.jmx;
import static org.junit.Assert.assertNotNull;

@Ignore
public class JmxTest {
    @Test
    public void bridge() {
        ConfigStorage configStorage = jmx(ConfigStorage.class);
        assertNotNull(configStorage);

        String appProperties = configStorage.printAppProperties();
        assertNotNull(appProperties);
    }
}