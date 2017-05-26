package com.haulmont.masquerade.components.impl.fresh;

import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.components.PopupButton;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.haulmont.masquerade.Selectors.byChain;
import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.tagName;

/**
 * Created by razgonyaev on 25.05.2017.
 */
public class PopupButtonImpl implements PopupButton {
    private final By by;
    private final SelenideElement impl;

    public PopupButtonImpl(By by) {
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

    public void click(String option) {
        openPopupContent()
                .select(option);
    }

    public PopupContent openPopupContent() {
        impl.shouldBe(visible)
                .click();

        PopupContentImpl popupContent = new PopupContentImpl(By.cssSelector("div.v-popupbutton-popup"));
        popupContent.shouldBe(visible);

        return popupContent;
    }

    public PopupContent getPopupContent() {
        PopupContentImpl popupContent = new PopupContentImpl(By.cssSelector("div.v-popupbutton-popup"));
        popupContent.shouldBe(visible);

        return popupContent;
    }


    private class PopupContentImpl implements PopupContent {
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
            $(byChain(by, tagName("span"), byText(option)))
                    .shouldBe(visible)
                    .click();
        }

        @Override
        public List<String> getOptions() {
            return $$(byChain(by, tagName("span"), className("v-button-caption"))).texts();
        }
    }
}
