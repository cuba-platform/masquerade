/*
 * Copyright (c) 2008-2017 Haulmont.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.haulmont.masquerade.components.impl;

import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.components.Notification;
import com.haulmont.masquerade.conditions.*;
import org.openqa.selenium.By;

import java.util.Objects;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.sys.matchers.InstanceOfCases.hasType;
import static com.leacox.motif.Motif.match;
import static org.openqa.selenium.By.className;

public class NotificationImpl extends AbstractSpecificConditionHandler<Notification> implements Notification {

    public static final By NOTIFICATION_CAPTION = className("v-Notification-caption");
    public static final By NOTIFICATION_DESCRIPTION = className("v-Notification-description");

    protected By by;
    protected SelenideElement impl;

    @SuppressWarnings("unused")
    public NotificationImpl(By ignoredBy) {
        // Notification uses custom overlay By
        this.by = className("v-Notification");
        this.impl = $(by);
    }

    @Override
    public SelenideElement getDelegate() {
        return impl;
    }

    @Override
    public By getBy() {
        return by;
    }

    @Override
    public boolean apply(SpecificCondition condition) {
        return match(condition)
                .when(hasType(Caption.class)).get(c-> {
                    SelenideElement captionImpl = $(byChain(by, NOTIFICATION_CAPTION));
                    return captionImpl.has(exactText(c.getCaption()));
                })
                .when(hasType(Description.class)).get(d-> {
                    SelenideElement descriptionImpl = $(byChain(by, NOTIFICATION_DESCRIPTION));
                    return descriptionImpl.has(exactText(d.getDescription()));
                })
                .when(hasType(CaptionContains.class)).get(cc-> {
                    SelenideElement captionImpl = $(byChain(by, NOTIFICATION_CAPTION));
                    return captionImpl.has(text(cc.getCaptionSubstring()));
                })
                .when(hasType(DescriptionContains.class)).get(dc-> {
                    SelenideElement descriptionImpl = $(byChain(by, NOTIFICATION_DESCRIPTION));
                    return descriptionImpl.has(text(dc.getDescriptionSubstring()));
                })
                .when(hasType(NotificationType.class)).get(t-> {
                    Type type = getTypeInternal();
                    return Objects.equals(type, t.getType());
                })
                .getMatch();
    }

    @Override
    public Type getType() {
        impl.shouldBe(visible);

        Type type = getTypeInternal();
        if (type == null) {
            throw new IllegalStateException("Unknown notification type");
        }

        return type;
    }

    protected Type getTypeInternal() {
        String classes = impl.getAttribute("class");
        if (classes.contains("warning")) {
            return Type.WARNING;
        }
        if (classes.contains("error")) {
            return Type.ERROR;
        }
        if (classes.contains("tray")) {
            return Type.TRAY;
        }
        if (classes.contains("humanized")) {
            return Type.HUMANIZED;
        }
        return null;
    }

    @Override
    public String getCaption() {
        impl.shouldBe(visible);

        SelenideElement captionImpl = $(byChain(by, NOTIFICATION_CAPTION));
        if (captionImpl.is(visible)) {
            return captionImpl.getText();
        } else {
            return null;
        }
    }

    @Override
    public String getDescription() {
        impl.shouldBe(visible);

        SelenideElement descriptionImpl = $(byChain(by, NOTIFICATION_DESCRIPTION));
        if (descriptionImpl.is(visible)) {
            return descriptionImpl.getText();
        } else {
            return null;
        }
    }

    @Override
    public Notification clickToClose() {
        impl.shouldBe(visible)
                .click();
        return this;
    }
}