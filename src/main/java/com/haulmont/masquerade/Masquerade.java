package com.haulmont.masquerade;

import com.haulmont.masquerade.components.PasswordField;
import com.haulmont.masquerade.components.TextField;
import com.haulmont.masquerade.components.impl.PasswordFieldImpl;
import com.haulmont.masquerade.components.impl.TextFieldImpl;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static com.haulmont.masquerade.Selectors.byCubaId;

public final class Masquerade {

    private static final Map<Class, Function<By, ?>> masks = new HashMap<>();
    static {
        masks.put(TextField.class, TextFieldImpl::new);
        masks.put(PasswordField.class, PasswordFieldImpl::new);
    }

    public static Mask mask(By by) {
        return new Mask(by);
    }

    public static Mask mask(String cubId) {
        return new Mask(byCubaId(cubId));
    }

    public static Mask mask() {
        return new Mask(null);
    }

    public static class Mask {
        private final By by;

        public Mask(By by) {
            this.by = by;
        }

        @SuppressWarnings("unchecked")
        public <T> T as(Class<T> clazz) {
            Function<By, ?> component = masks.get(clazz);
            if (by != null && component != null) {
                return (T) component.apply(by);
            } else {
                // custom composite

                T instance;
                try {
                    instance = clazz.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException("Unable to instantiate composite");
                }

                // connect fields

                Field[] allFields = FieldUtils.getAllFields(clazz);
                for (Field field : allFields) {
                    Connect[] connects = field.getAnnotationsByType(Connect.class);
                    if (connects != null) {
                        String fieldName = field.getName();

                        By fieldBy;
                        if (by != null) {
                            fieldBy = new ByChained(by, byCubaId(fieldName));
                        } else {
                            fieldBy = byCubaId(fieldName);
                        }

                        try {
                            field.setAccessible(true);
                            field.set(instance, mask(fieldBy).as(field.getType()));
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException("Unable to connect @Connect field " + fieldName, e);
                        }
                    }
                }

                return instance;
            }
        }
    }
}