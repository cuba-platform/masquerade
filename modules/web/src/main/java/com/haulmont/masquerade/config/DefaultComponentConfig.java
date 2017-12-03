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
        components.put(PopupButton.class, PopupButtonImpl::new);
        components.put(DateField.class, DateFieldImpl::new);
        components.put(DateTimeField.class, DateTimeFieldImpl::new);
        components.put(TimeField.class, TimeFieldImpl::new);
        components.put(FileUploadField.class, FileUploadFieldImpl::new);
        components.put(MaskedField.class, MaskedFieldImpl::new);
        // stubs
        components.put(BoxLayout.class, BoxLayoutImpl::new);
        components.put(TabSheet.class, TabSheetImpl::new);
        components.put(DialogWindow.class, DialogWindowImpl::new);
        components.put(FieldGroup.class, FieldGroupImpl::new);
        components.put(GroupBox.class, GroupBoxImpl::new);
        components.put(LookupPickerField.class, LookupPickerFieldImpl::new);
        components.put(OptionsGroup.class, OptionsGroupImpl::new);
        components.put(PickerField.class, PickerFieldImpl::new);
        components.put(SourceCodeEditor.class, SourceCodeEditorImpl::new);
        components.put(Tree.class, TreeImpl::new);

        return components;
    }
}