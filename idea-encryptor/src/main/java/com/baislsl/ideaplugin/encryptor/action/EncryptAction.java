package com.baislsl.ideaplugin.encryptor.action;

import com.baislsl.ideaplugin.encryptor.core.EncryptExecutor;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;


public class EncryptAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        new EncryptExecutor(e.getProject(), e.getData(PlatformDataKeys.EDITOR).getDocument())
                .conduct();
    }
}
