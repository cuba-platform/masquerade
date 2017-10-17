package com.haulmont.masquerade.components.impl.fresh;

import com.haulmont.masquerade.Components;
import com.haulmont.masquerade.components.PickerField;
import com.haulmont.masquerade.components.impl.AbstractComponent;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.Selectors.byCubaId;
import static com.haulmont.masquerade.sys.TagNames.DIV;
import static com.haulmont.masquerade.sys.TagNames.INPUT;
import static com.haulmont.masquerade.sys.VaadinClassNames.DISABLED_CLASSNAME;

public class PickerFieldImpl extends AbstractComponent<PickerField> implements PickerField {
    public PickerFieldImpl(By by) {
        super(by);
    }

    @Override
    public void triggerAction(Action action) {
        $(byChain(by, DIV, byCubaId(action.getId())))
                .shouldBe(visible)
                .shouldNotHave(cssClass(DISABLED_CLASSNAME))
                .click();
    }

    @Override
    public <T> T triggerAction(Class<T> clazz, Action action) {
        triggerAction(action);
        return Components.wire(clazz);
    }

    @Override
    public String getValue() {
        return $(byChain(by, INPUT))
                .shouldBe(visible)
                .shouldBe(enabled)
                .getValue();
    }

}