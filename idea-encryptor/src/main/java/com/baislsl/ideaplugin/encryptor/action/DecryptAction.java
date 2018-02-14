package com.baislsl.ideaplugin.encryptor.action;

import com.baislsl.ideaplugin.encryptor.core.DecryptExecutor;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;


public class DecryptAction extends AnAction {
    private DecryptExecutor executor;

    @Override
    public void actionPerformed(AnActionEvent e) {
        Document document = e.getData(PlatformDataKeys.EDITOR).getDocument();
        executor = new DecryptExecutor(e.getProject(), document.getText(),
                () -> WriteCommandAction.runWriteCommandAction(
                        e.getProject(),
                        () -> document.setText(executor.getResult()))
        );
        executor.conduct();
    }
}
