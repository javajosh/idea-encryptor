package com.baislsl.ideaplugin.encryptor.action;

import com.baislsl.ideaplugin.encryptor.core.DecryptExecutor;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;


public class DecryptAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        new DecryptExecutor(e.getProject(), e.getData(PlatformDataKeys.EDITOR).getDocument())
                .conduct();
    }
}
