package com.haulmont.masquerade.components.impl.fresh;

import com.haulmont.masquerade.Wire;
import com.haulmont.masquerade.components.Button;
import com.haulmont.masquerade.components.PickerField;
import com.haulmont.masquerade.components.impl.AbstractComponent;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.sys.TagNames.INPUT;

public class PickerFieldImpl extends AbstractComponent<PickerField> implements PickerField {
    public PickerFieldImpl(By by) {
        super(by);
    }

    @Override
    public void triggerAction(Action action) {
        throw new NotImplementedException("This UI component still in development");
    }

    @Override
    public <T> T triggerAction(Class<T> clazz, Action action) {
        throw new NotImplementedException("This UI component still in development");
    }

    @Override
    public String getValue() {
        return $(byChain(by, INPUT))
                .shouldBe(visible)
                .shouldBe(enabled)
                .getValue();
    }



}