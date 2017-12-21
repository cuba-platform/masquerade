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

package com.haulmont.masquerade.composite

import com.haulmont.masquerade.Wire
import com.haulmont.masquerade.base.Composite
import com.haulmont.masquerade.components.Button
import com.haulmont.masquerade.components.CheckBox
import com.haulmont.masquerade.components.Label
import com.haulmont.masquerade.components.LookupField
import com.haulmont.masquerade.components.PasswordField
import com.haulmont.masquerade.components.TextField

class GroovyLoginWindow extends Composite<GroovyLoginWindow> {
    @Wire
    Label welcomeLabel

    @Wire
    TextField loginField

    @Wire
    PasswordField passwordField

    @Wire
    Button loginButton

    @Wire
    CheckBox rememberMeCheckBox

    @Wire
    LookupField localesSelect
}