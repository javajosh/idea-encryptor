package com.baislsl.ideaplugin.encryptor.ui;

import com.baislsl.ideaplugin.encryptor.core.method.EncryptMethod;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

public class KeyQueryDialog {
    private Project project;
    private EncryptMethod method;

    public KeyQueryDialog(Project project, EncryptMethod method) {
        this.project = project;
        this.method = method;
    }

    public String getKey() {
        return Messages.showInputDialog(project,
                "Input your key. " + method.getHint(),
                "Key",
                Messages.getQuestionIcon());
    }
}
