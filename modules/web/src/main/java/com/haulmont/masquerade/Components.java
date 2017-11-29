package com.haulmont.masquerade;

import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.base.SelenideElementWrapper;
import com.haulmont.masquerade.config.ComponentConfig;
import com.haulmont.masquerade.config.DefaultComponentConfig;
import com.haulmont.masquerade.sys.LoggingInvocationHandler;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.Annotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import static com.codeborne.selenide.Selenide.$;
import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.Selectors.byPath;

public class Components {
    private static final By BODY_MARKER_BY = By.tagName("body");
    private static final String CUBA_VERSION_SYSTEM_PROPERTY = "cuba.version";

    private static final Map<Class, Function<By, ?>> components = new ConcurrentHashMap<>();

    static {
        ComponentConfig defaultConfig = new DefaultComponentConfig();
        components.putAll(defaultConfig.getComponents());

        String cubaVersion = System.getProperty(CUBA_VERSION_SYSTEM_PROPERTY);
        if (cubaVersion != null && "5.x".equals(cubaVersion)) {
            // import additional implementations or replace default
        }

        // import implementations from project
        ServiceLoader<ComponentConfig> configs = ServiceLoader.load(ComponentConfig.class);
        for (ComponentConfig componentConfig : configs) {
            LoggerFactory.getLogger(Components.class)
                    .info("Loading components from {}", componentConfig.getClass());

            components.putAll(componentConfig.getComponents());
        }
    }

    public static <T> void register(Class<T> clazz, Function<By, T> componentSupplier) {
        components.put(clazz, componentSupplier);
    }

    public static <T> T wire(Class<T> clazz) {
        Wire clazzWire = clazz.getAnnotation(Wire.class);
        By targetBy;
        if (clazzWire != null && clazzWire.path().length != 0) {
            targetBy = byPath(clazzWire.path());
        } else {
            targetBy = BODY_MARKER_BY;
        }

        return wireClassBy(clazz, targetBy);
    }

    public static <T> T wire(Class<T> clazz, String... path) {
        return wireClassBy(clazz, byPath(path));
    }

    public static <T> T wire(Class<T> clazz, By by) {
        return wireClassBy(clazz, by);
    }

    public static <T> T _$(Class<T> clazz) {
        return wire(clazz);
    }

    public static <T> T _$(Class<T> clazz, String... path) {
        return wire(clazz, path);
    }

    public static <T> T _$(Class<T> clazz, By by) {
        return wire(clazz, by);
    }

    protected static <T> T wireClassBy(Class<T> clazz, By by) {
        if (by == null) {
            throw new IllegalArgumentException("By cannot be null");
        }
        if (clazz == null) {
            throw new IllegalArgumentException("Class cannot be null");
        }

        Function<By, ?> component = components.get(clazz);
        if (component != null) {
            // if it is an interface - we can proxy it and add logging automatically
            T instance = clazz.cast(component.apply(by));

            return proxyComponent(clazz, instance);
        } else {
            // custom composite
            T instance;
            try {
                instance = clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException("Unable to instantiate composite " + clazz.getName(), e);
            }

            // connect fields
            Field[] allFields = FieldUtils.getAllFields(clazz);
            for (Field field : allFields) {
                Object fieldValue = getTargetFieldValue(clazz, field, by);

                if (fieldValue != null) {
                    try {
                        field.setAccessible(true);
                        field.set(instance, fieldValue);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("Unable to inject field " + field.getName(), e);
                    }
                }
            }

            return instance;
        }
    }

    protected static Object getTargetFieldValue(Class clazz, Field field, By parentBy) {
        String fieldName = field.getName();

        Wire wire = field.getAnnotation(Wire.class);

        Object fieldValue;
        if (wire != null) {
            if (field.getType() == SelenideElement.class) {
                fieldValue = $(parentBy);
            } else if (field.getType() == By.class) {
                fieldValue = parentBy;
            } else if (field.getType() == Logger.class) {
                fieldValue = LoggerFactory.getLogger(clazz);
            } else {
                String[] path = wire.path();
                if (path.length == 0) {
                    path = new String[]{fieldName};
                }

                By fieldBy;
                if (parentBy == BODY_MARKER_BY) {
                    fieldBy = byPath(path);
                } else {
                    fieldBy = byChain(parentBy, byPath(path));
                }
                fieldValue = wireClassBy(field.getType(), fieldBy);
            }
        } else {
            FindBy findBy = field.getAnnotation(FindBy.class);
            if (findBy != null) {
                By selector = new Annotations(field).buildBy();

                By fieldBy;
                if (parentBy == BODY_MARKER_BY) {
                    fieldBy = selector;
                } else {
                    fieldBy = byChain(parentBy, selector);
                }

                fieldValue = wireClassBy(field.getType(), fieldBy);
            } else {
                fieldValue = null;
            }
        }

        return fieldValue;
    }

    @SuppressWarnings("unchecked")
    public static <T> T proxyComponent(Class<T> componentClass, T target) {
        LoggingInvocationHandler invocationHandler = new LoggingInvocationHandler(componentClass, target);
        invocationHandler.setProxyFactory((interfaceClass, object) -> {
            if (SelenideElementWrapper.class.isAssignableFrom(interfaceClass)
                    && interfaceClass.isInterface()) {
                return proxyComponent(interfaceClass, object);
            }

            return object;
        });

        return (T) Proxy.newProxyInstance(
                componentClass.getClassLoader(),
                new Class<?>[]{componentClass},
                invocationHandler);
    }
}