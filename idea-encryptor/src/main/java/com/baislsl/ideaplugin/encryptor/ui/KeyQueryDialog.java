package com.baislsl.ideaplugin.encryptor.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

public class KeyQueryDialog {
    private Project project;

    public KeyQueryDialog(Project project) {
        this.project = project;
    }

    public String getKey() {
        return Messages.showInputDialog(project,
                "Input your key",
                "Key",
                Messages.getQuestionIcon());
    }
}
