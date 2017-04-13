package com.haulmont.masquerade;

import com.haulmont.masquerade.components.Button;
import com.haulmont.masquerade.components.PasswordField;
import com.haulmont.masquerade.components.TextField;
import com.haulmont.masquerade.components.impl.fresh.ButtonImpl;
import com.haulmont.masquerade.components.impl.fresh.PasswordFieldImpl;
import com.haulmont.masquerade.components.impl.fresh.TextFieldImpl;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.openqa.selenium.By;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.Selectors.byCubaId;

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

    public static Mask mask() {
        return new Mask(null);
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

                // connect fields

                Field[] allFields = FieldUtils.getAllFields(clazz);
                for (Field field : allFields) {
                    Connect[] connects = field.getAnnotationsByType(Connect.class);
                    if (connects != null && connects.length > 0) {
                        String fieldName = field.getName();

                        By fieldBy;
                        if (by != null) {
                            fieldBy = byChain(by, byCubaId(fieldName));
                        } else {
                            fieldBy = byCubaId(fieldName);
                        }

                        try {
                            field.setAccessible(true);
                            field.set(instance, mask(fieldBy).with(field.getType()));
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