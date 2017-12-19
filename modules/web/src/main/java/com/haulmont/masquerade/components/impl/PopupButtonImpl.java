package com.haulmont.masquerade.components.impl;

import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.components.PopupButton;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.sys.TagNames.SPAN;
import static com.haulmont.masquerade.sys.VaadinClassNames.disabledClass;
import static org.openqa.selenium.By.className;

public class PopupButtonImpl extends AbstractComponent<PopupButton> implements PopupButton {

    public PopupButtonImpl(By by) {
        super(by);
    }

    @Override
    public void click(String option) {
        openPopupContent()
                .select(option);
    }

    @Override
    public PopupContent openPopupContent() {
        impl.shouldBe(visible)
                .shouldNotHave(disabledClass)
                .click();

        PopupContentImpl popupContent = new PopupContentImpl(By.cssSelector("div.v-popupbutton-popup"));
        popupContent.shouldBe(visible);

        return popupContent;
    }

    @Override
    public PopupContent getPopupContent() {
        PopupContentImpl popupContent = new PopupContentImpl(By.cssSelector("div.v-popupbutton-popup"));
        popupContent.shouldBe(visible);

        return popupContent;
    }

    public static class PopupContentImpl implements PopupContent {
        private final By by;
        private final SelenideElement impl;

        public PopupContentImpl(By by) {
            this.by = by;
            this.impl = $(by);
        }

        @Override
        public By getBy() {
            return by;
        }

        @Override
        public SelenideElement getDelegate() {
            return impl;
        }

        @Override
        public void select(String option) {
            $(byChain(by, SPAN, byText(option)))
                    .parent().parent()
                    .shouldBe(visible)
                    .shouldNotHave(disabledClass)
                    .click();
        }

        @Override
        public List<String> getOptions() {
            return $$(byChain(by, SPAN, className("v-button-caption"))).texts();
        }
    }
}