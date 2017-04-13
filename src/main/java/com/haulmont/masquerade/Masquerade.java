package com.haulmont.masquerade;

import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.components.Button;
import com.haulmont.masquerade.components.PasswordField;
import com.haulmont.masquerade.components.TextField;
import com.haulmont.masquerade.components.impl.fresh.ButtonImpl;
import com.haulmont.masquerade.components.impl.fresh.PasswordFieldImpl;
import com.haulmont.masquerade.components.impl.fresh.TextFieldImpl;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import static com.codeborne.selenide.Selenide.$;
import static com.haulmont.masquerade.Selectors.*;

public final class Masquerade {

    private static final Map<Class, Function<By, ?>> masks = new ConcurrentHashMap<>();

    public static final String CUBA_VERSION_SYSTEM_PROPERTY = "cuba.version";

    static {
        masks.put(TextField.class, TextFieldImpl::new);
        masks.put(PasswordField.class, PasswordFieldImpl::new);
        masks.put(Button.class, ButtonImpl::new);

        String cubaVersion = System.getProperty(CUBA_VERSION_SYSTEM_PROPERTY);
        if (cubaVersion == null || "5.x".equals(cubaVersion)) {
            // import additional implementations or replace default
        }
    }

    public static Mask mask(By by) {
        return new Mask(by);
    }

    public static Mask mask(String cubId) {
        return new Mask(byCubaId(cubId));
    }

    public static <T> T mask(Class<T> clazz) {
        return new Mask(null).with(clazz);
    }

    public static class Mask {
        private final By by;

        public Mask(By by) {
            this.by = by;
        }

        @SuppressWarnings("unchecked")
        public <T> T with(Class<T> clazz) {
            Function<By, ?> component = masks.get(clazz);
            if (by != null && component != null) {
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
                    Connect clazzConnect = clazz.getAnnotation(Connect.class);
                    if (clazzConnect != null && clazzConnect.path().length != 0) {
                        targetBy = byPath(clazzConnect.path());
                    }
                }

                // connect fields

                Field[] allFields = FieldUtils.getAllFields(clazz);
                for (Field field : allFields) {
                    Connect connect = field.getAnnotation(Connect.class);
                    if (connect != null) {
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
                            String[] path = connect.path();
                            if (path.length == 0) {
                                path = new String[]{fieldName};
                            }

                            By fieldBy;
                            if (targetBy != null) {
                                fieldBy = byChain(targetBy, byPath(path));
                            } else {
                                fieldBy = byPath(path);
                            }

                            fieldValue = mask(fieldBy).with(field.getType());
                        }

                        try {
                            field.setAccessible(true);
                            field.set(instance, fieldValue);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException("Unable to set @Connect field " + fieldName, e);
                        }
                    }
                }

                return instance;
            }
        }
    }

    public static <T> void registerMask(Class<T> clazz, Function<By, T> maskSupplier) {
        masks.put(clazz, maskSupplier);
    }
}