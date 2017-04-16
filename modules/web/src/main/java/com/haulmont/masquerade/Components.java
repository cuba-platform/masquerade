package com.haulmont.masquerade;

import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.components.*;
import com.haulmont.masquerade.components.impl.fresh.*;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import static com.codeborne.selenide.Selenide.$;
import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.Selectors.byPath;

public class Components {
    private static final Map<Class, Function<By, ?>> components = new ConcurrentHashMap<>();

    public static final String CUBA_VERSION_SYSTEM_PROPERTY = "cuba.version";

    static {
        components.put(Untyped.class, UntypedImpl::new);
        components.put(TextField.class, TextFieldImpl::new);
        components.put(TextArea.class, TextAreaImpl::new);
        components.put(PasswordField.class, PasswordFieldImpl::new);
        components.put(Button.class, ButtonImpl::new);
        components.put(CheckBox.class, CheckBoxImpl::new);

        String cubaVersion = System.getProperty(CUBA_VERSION_SYSTEM_PROPERTY);
        if (cubaVersion == null || "5.x".equals(cubaVersion)) {
            // import additional implementations or replace default
        }
    }

    public static <T> T wire(Class<T> clazz) {
        return wireClassBy(clazz, null);
    }

    public static <T> T wire(Class<T> clazz, String... path) {
        return wireClassBy(clazz, byPath(path));
    }

    public static <T> T wire(Class<T> clazz, By by) {
        return wireClassBy(clazz, by);
    }

    protected static <T> T wireClassBy(Class<T> clazz, By by) {
        Function<By, ?> component = components.get(clazz);
        if (by != null && component != null) {
            //noinspection unchecked
            return (T) component.apply(by);
        } else {
            // custom composite

            T instance;
            try {
                instance = clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException("Unable to instantiate composite", e);
            }

            By targetBy = by;
            if (targetBy == null) {
                Wire clazzWire = clazz.getAnnotation(Wire.class);
                if (clazzWire != null && clazzWire.path().length != 0) {
                    targetBy = byPath(clazzWire.path());
                }
            }

            // connect fields

            Field[] allFields = FieldUtils.getAllFields(clazz);
            for (Field field : allFields) {
                Wire wire = field.getAnnotation(Wire.class);
                if (wire != null) {
                    String fieldName = field.getName();

                    Object fieldValue;
                    if (field.getType() == SelenideElement.class) {
                        By connectBy = targetBy;
                        if (connectBy == null) {
                            connectBy = By.tagName("body");
                        }

                        fieldValue = $(connectBy);
                    } else if (field.getType() == By.class) {
                        By connectBy = targetBy;
                        if (connectBy == null) {
                            connectBy = By.tagName("body");
                        }

                        fieldValue = connectBy;
                    } else if (field.getType() == Logger.class) {
                        fieldValue = LoggerFactory.getLogger(clazz);
                    } else {
                        String[] path = wire.path();
                        if (path.length == 0) {
                            path = new String[]{fieldName};
                        }

                        By fieldBy;
                        if (targetBy != null) {
                            fieldBy = byChain(targetBy, byPath(path));
                        } else {
                            fieldBy = byPath(path);
                        }

                        fieldValue = wire(field.getType(), fieldBy);
                    }

                    try {
                        field.setAccessible(true);
                        field.set(instance, fieldValue);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("Unable to set @Wire field " + fieldName, e);
                    }
                }
            }

            return instance;
        }
    }

    public static <T> void register(Class<T> clazz, Function<By, T> componentSupplier) {
        components.put(clazz, componentSupplier);
    }
}