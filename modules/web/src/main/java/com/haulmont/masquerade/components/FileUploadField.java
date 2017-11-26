package com.haulmont.masquerade.components;

import java.io.File;

public interface FileUploadField extends Component<FileUploadField> {
    void upload(File file);

    void clear();
}