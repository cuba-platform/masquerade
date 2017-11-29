package com.haulmont.masquerade.components;

import com.haulmont.masquerade.util.Log;

import java.io.File;

public interface FileUploadField extends Component<FileUploadField> {
    @Log
    void upload(File file);

    @Log
    void clear();
}