package com.baislsl.ideaplugin.encryptor.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DetectConfirmDialog extends DialogWrapper {
    public final static int USE_EXIT_CODE = 1;
    public final static int EXIT_CODE = 2;
    private String information;

    public DetectConfirmDialog(Project project, String information) {
        super(project, false);
        this.information = information;
        init();
        setTitle("Encrypt Tool");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return new JTextArea(information);
    }

    @NotNull
    @Override
    protected Action[] createActions() {
        Action[] actions = new Action[2];

        actions[0] = new DialogWrapperExitAction("OK, Use Encrypt Tool", USE_EXIT_CODE);
        actions[1] = new DialogWrapperExitAction("No", EXIT_CODE);

        return actions;
    }
}
