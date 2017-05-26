package com.haulmont.masquerade;

import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.components.*;
import com.haulmont.masquerade.components.impl.fresh.*;
import com.haulmont.masquerade.components.impl.legacy.Table56Impl;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.Annotations;
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
    private static final By BODY_MARKER_BY = By.tagName("body");
    private static final String CUBA_VERSION_SYSTEM_PROPERTY = "cuba.version";

    private static final Map<Class, Function<By, ?>> components = new ConcurrentHashMap<>();

    static {
        components.put(Untyped.class, UntypedImpl::new);
        components.put(TextField.class, TextFieldImpl::new);
        components.put(TextArea.class, TextAreaImpl::new);
        components.put(PasswordField.class, PasswordFieldImpl::new);
        components.put(Button.class, ButtonImpl::new);
        components.put(Label.class, LabelImpl::new);
        components.put(CheckBox.class, CheckBoxImpl::new);
        components.put(AppMenu.class, AppMenuImpl::new);
        components.put(LookupField.class, LookupFieldImpl::new);
        components.put(Table.class, Table56Impl::new);
        components.put(PopupButton.class, PopupButtonImpl::new);

        String cubaVersion = System.getProperty(CUBA_VERSION_SYSTEM_PROPERTY);
        if (cubaVersion != null && "5.x".equals(cubaVersion)) {
            // import additional implementations or replace default
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
            return clazz.cast(component.apply(by));
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
                String fieldName = field.getName();

                Wire wire = field.getAnnotation(Wire.class);
                if (wire != null) {
                    Object fieldValue;
                    if (field.getType() == SelenideElement.class) {
                        fieldValue = $(by);
                    } else if (field.getType() == By.class) {
                        fieldValue = by;
                    } else if (field.getType() == Logger.class) {
                        fieldValue = LoggerFactory.getLogger(clazz);
                    } else {
                        String[] path = wire.path();
                        if (path.length == 0) {
                            path = new String[]{fieldName};
                        }

                        By fieldBy;
                        if (by == BODY_MARKER_BY) {
                            fieldBy = byPath(path);
                        } else {
                            fieldBy = byChain(by, byPath(path));
                        }
                        fieldValue = wireClassBy(field.getType(), fieldBy);
                    }

                    try {
                        field.setAccessible(true);
                        field.set(instance, fieldValue);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("Unable to set @Wire field " + fieldName, e);
                    }
                } else {
                    FindBy findBy = field.getAnnotation(FindBy.class);
                    if (findBy != null) {
                        By selector = new Annotations(field).buildBy();

                        By fieldBy;
                        if (by == BODY_MARKER_BY) {
                            fieldBy = selector;
                        } else {
                            fieldBy = byChain(by, selector);
                        }

                        Object fieldValue = wireClassBy(field.getType(), fieldBy);

                        try {
                            field.setAccessible(true);
                            field.set(instance, fieldValue);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException("Unable to set @FindBy field " + fieldName, e);
                        }
                    }
                }
            }

            return instance;
        }
    }
}