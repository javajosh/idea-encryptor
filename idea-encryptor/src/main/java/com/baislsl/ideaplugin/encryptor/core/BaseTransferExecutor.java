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
    protected String text;
    protected Project project;
    protected String key;
    protected EncryptMethod method;
    protected Runnable executor;
    protected String result;

    public BaseTransferExecutor(Project project, String text, Runnable executor) {
        this.project = project;
        this.text = text;
        this.executor = executor;
    }

    public void conduct() {
        ConfigureDialog configureDialog = new ConfigureDialog(project);
        if (method != null) configureDialog.setMethod(method);
        configureDialog.show();
        int exitCode = configureDialog.getExitCode();
        if (exitCode == DialogWrapper.OK_EXIT_CODE) {
            this.key = configureDialog.getKey();
            this.method = configureDialog.getMethod();
            result = conductTransfer();
            executor.run();
        }
    }


    protected abstract String conductTransfer();

    public void setProject(Project project) {
        this.project = project;
    }

    public void setMethod(EncryptMethod method) {
        this.method = method;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getResult() {
        return result;
    }
}
