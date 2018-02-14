package com.baislsl.ideaplugin.encryptor.action;

import com.baislsl.ideaplugin.encryptor.ui.ToolKitDialog;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.DialogWrapper;

public class ToolKit extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        DialogWrapper wrapper = new ToolKitDialog(e.getProject());
        wrapper.show();
    }
}
