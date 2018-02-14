package com.baislsl.ideaplugin.encryptor.core;

import com.baislsl.ideaplugin.encryptor.core.method.EncryptMethod;
import com.baislsl.ideaplugin.encryptor.ui.ConfigureDialog;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;

/**
 * @see DecryptExecutor
 * @see EncryptExecutor
 */
public abstract class BaseTransferExecutor {
    protected Document document;
    protected Project project;
    protected String key;
    protected EncryptMethod method;

    public BaseTransferExecutor(Project project, Document document) {
        this.project = project;
        this.document = document;
    }

    public void conduct() {
        ConfigureDialog configureDialog = new ConfigureDialog(project);
        if (method != null) configureDialog.setMethod(method);
        configureDialog.show();
        int exitCode = configureDialog.getExitCode();
        if (exitCode == DialogWrapper.OK_EXIT_CODE) {
            this.key = configureDialog.getKey();
            this.method = configureDialog.getMethod();
            conductTransfer();
        }
    }


    protected abstract void conductTransfer();

    public void setProject(Project project) {
        this.project = project;
    }

    public void setMethod(EncryptMethod method) {
        this.method = method;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

}
