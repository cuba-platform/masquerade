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

package com.haulmont.masquerade.sys.matchers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.conditions.Value;
import com.haulmont.masquerade.conditions.ValueContains;
import com.leacox.motif.matching.FluentMatching;
import com.leacox.motif.matching.FluentMatchingR;

import static com.google.common.base.Strings.nullToEmpty;
import static com.haulmont.masquerade.Conditions.*;
import static com.haulmont.masquerade.sys.VaadinClassNames.*;
import static com.haulmont.masquerade.sys.matchers.InstanceOfCases.hasType;
import static com.leacox.motif.MatchesExact.eq;

public final class ConditionCases {
    private ConditionCases() {
    }

    public static FluentMatchingR<Condition, Boolean> componentApply(FluentMatching<Condition> matching,
                                                                     SelenideElement impl) {
        return matching
                .when(eq(ENABLED)).get(() ->
                        !impl.has(disabledClass)
                )
                .when(eq(DISABLED)).get(() ->
                        impl.has(disabledClass)
                );
    }

    @SuppressWarnings("CodeBlock2Expr")
    public static FluentMatchingR<Condition, Boolean> fieldApply(FluentMatching<Condition> matching,
                                                                 SelenideElement impl,
                                                                 SelenideElement inputImpl) {
        return matching
                .when(hasType(Value.class)).get(v -> {
                    String expectedValue = nullToEmpty(v.getExpectedValue());
                    return inputImpl.is(Condition.visible) && inputImpl.has(Condition.exactValue(expectedValue));
                })
                .when(hasType(ValueContains.class)).get(v -> {
                    String expectedValue = nullToEmpty(v.getExpectedValueSubstring());
                    return inputImpl.is(Condition.visible) && inputImpl.has(Condition.value(expectedValue));
                })
                .when(eq(ENABLED)).get(() -> {
                    return !impl.has(disabledClass);
                })
                .when(eq(DISABLED)).get(() -> {
                    return impl.has(disabledClass);
                })
                .when(eq(REQUIRED)).get(() -> {
                    return impl.has(requiredClass);
                })
                .when(eq(READONLY)).get(() -> {
                    return impl.has(readonlyClass);
                })
                .when(eq(EDITABLE)).get(() -> {
                    return !impl.has(readonlyClass);
                });
    }
}