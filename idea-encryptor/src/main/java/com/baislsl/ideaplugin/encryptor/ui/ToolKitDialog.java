package com.baislsl.ideaplugin.encryptor.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class ToolKitDialog extends DialogWrapper {
    private Project project;

    public ToolKitDialog(Project project) {
        super(project, false);
        init();
        this.project = project;
        setTitle("Encrypt Tool Kit Window");
    }

    @NotNull
    @Override
    protected Action[] createActions() {
        return new Action[]{};
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return new ToolKitCore();
    }
}
