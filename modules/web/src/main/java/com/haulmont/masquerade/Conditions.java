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

package com.haulmont.masquerade;

import com.codeborne.selenide.Condition;
import com.haulmont.masquerade.components.Component;
import com.haulmont.masquerade.components.Notification;
import com.haulmont.masquerade.conditions.*;

import java.util.Arrays;
import java.util.List;

/**
 * Conditions for {@link Component#should(Condition...)} methods.
 */
public class Conditions {
    protected Conditions() {
    }

    public static final Condition ENABLED = new SpecificCondition("enabled");

    public static final Condition DISABLED = new SpecificCondition("disabled");

    public static final Condition VISIBLE = Condition.visible;

    public static final Condition HIDDEN = Condition.hidden;

    public static final Condition EXIST = Condition.exist;

    public static final Condition APPEAR = VISIBLE;

    public static final Condition APPEARS = VISIBLE;

    public static final Condition DISAPPEARS = HIDDEN;

    public static final Condition DISAPPEAR = HIDDEN;

    public static final Condition READONLY = new SpecificCondition("readonly");

    public static final Condition EDITABLE = new SpecificCondition("editable");

    public static final Condition REQUIRED = new SpecificCondition("required");

    public static final Condition CHECKED = new SpecificCondition("checked");

    public static final Condition SELECTED = new SpecificCondition("selected");

    public static final Condition EXPANDED = new SpecificCondition("expanded");

    public static final Condition COLLAPSED = new SpecificCondition("collapsed");

    public static Condition caption(String caption) {
        return new Caption(caption);
    }

    public static Condition captionContains(String caption) {
        return new CaptionContains(caption);
    }

    public static Condition description(String description) {
        return new Description(description);
    }

    public static Condition descriptionContains(String description) {
        return new DescriptionContains(description);
    }

    public static Condition value(String expectedValue) {
        return new Value(expectedValue);
    }

    public static Condition valueContains(String expectedValueSubstring) {
        return new ValueContains(expectedValueSubstring);
    }

    public static Condition containOptions(List<String> options) {
        return new ContainOptions(options);
    }

    public static Condition containOptions(String... options) {
        return new ContainOptions(Arrays.asList(options));
    }

    public static Condition options(List<String> options) {
        return new Options(options);
    }

    public static Condition options(String... options) {
        return new Options(Arrays.asList(options));
    }

    public static Condition visibleOptions(List<String> options) {
        return new Options(options);
    }

    public static Condition visibleOptions(String... options) {
        return new Options(Arrays.asList(options));
    }

    public static Condition visibleOptionsCount(int count) {
        return new OptionsCount(count);
    }

    public static Condition optionsCount(int count) {
        return new OptionsCount(count);
    }

    public static Condition dateValue(String expectedValue) {
        return new DateValue(expectedValue);
    }

    public static Condition timeValue(String expectedValue) {
        return new TimeValue(expectedValue);
    }

    public static Condition type(Notification.Type type) {
        return new NotificationType(type);
    }
}