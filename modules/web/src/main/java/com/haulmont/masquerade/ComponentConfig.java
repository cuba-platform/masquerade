package com.haulmont.masquerade;

import org.openqa.selenium.By;

import java.util.Map;
import java.util.function.Function;

/**
 * SPI interface for {@link Components} factory.
 *
 * Implement this interface in your project and create file
 * "META-INF/services/com.haulmont.masquerade.ComponentConfig" in your classpath with FQN of your implementation.
 *
 * <pre><code>
 * public class CustomComponentConfig implements ComponentConfig {
 *    {@literal @}Override
 *     public Map&lt;Class, Function&lt;By, ?&gt;&gt; getComponents() {
 *         return ImmutableMap.of(Untyped.class, UntypedImpl::new);
 *     }
 * }</code></pre>
 */
public interface ComponentConfig {
    Map<Class, Function<By, ?>> getComponents();
}