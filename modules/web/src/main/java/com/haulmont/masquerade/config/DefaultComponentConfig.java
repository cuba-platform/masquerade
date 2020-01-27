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

package com.haulmont.masquerade.config;

import com.haulmont.masquerade.components.*;
import com.haulmont.masquerade.components.impl.*;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class DefaultComponentConfig implements ComponentConfig {
    @Override
    public Map<Class, Function<By, ?>> getComponents() {
        Map<Class, Function<By, ?>> components = new HashMap<>();

        components.put(Untyped.class, UntypedImpl::new);
        components.put(TextField.class, TextFieldImpl::new);
        components.put(TextArea.class, TextAreaImpl::new);
        components.put(PasswordField.class, PasswordFieldImpl::new);
        components.put(Button.class, ButtonImpl::new);
        components.put(Label.class, LabelImpl::new);
        components.put(CheckBox.class, CheckBoxImpl::new);
        components.put(AppMenu.class, AppMenuImpl::new);
        components.put(LookupField.class, LookupFieldImpl::new);
        components.put(Table.class, TableImpl::new);
        components.put(GroupTable.class, GroupTableImpl::new);
        components.put(PopupButton.class, PopupButtonImpl::new);
        components.put(DateField.class, DateFieldImpl::new);
        components.put(DateTimeField.class, DateTimeFieldImpl::new);
        components.put(TimeField.class, TimeFieldImpl::new);
        components.put(FileUploadField.class, FileUploadFieldImpl::new);
        components.put(MaskedField.class, MaskedFieldImpl::new);
        components.put(GroupBox.class, GroupBoxImpl::new);
        components.put(LookupPickerField.class, LookupPickerFieldImpl::new);
        components.put(OptionsGroup.class, OptionsGroupImpl::new);
        components.put(PickerField.class, PickerFieldImpl::new);
        components.put(Notification.class, NotificationImpl::new);
        components.put(DialogWindow.class, DialogWindowImpl::new);
        components.put(SideMenu.class, SideMenuImpl::new);
        // stubs
        components.put(BoxLayout.class, BoxLayoutImpl::new);
        components.put(TabSheet.class, TabSheetImpl::new);
        components.put(FieldGroup.class, FieldGroupImpl::new);
        components.put(SourceCodeEditor.class, SourceCodeEditorImpl::new);
        components.put(Tree.class, TreeImpl::new);

        components.put(DataGrid.class, DataGridImpl::new);

        return components;
    }
}