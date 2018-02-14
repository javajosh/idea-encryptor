package com.baislsl.ideaplugin.encryptor.action;

import com.baislsl.ideaplugin.encryptor.core.EncryptExecutor;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import org.apache.maven.model.Model;

public class EncryptSelectedAction extends AnAction {
    private EncryptExecutor executor;

    @Override
    public void actionPerformed(AnActionEvent e) {
        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        SelectionModel model = editor.getSelectionModel();
        int start = model.getSelectionStart(),
                end = model.getSelectionEnd();
        String text = model.getSelectedText();
        if(text == null) {
            text = "";
        }
        executor = new EncryptExecutor(e.getProject(), text,
                () -> WriteCommandAction.runWriteCommandAction(
                        e.getProject(),
                        () -> {
                            editor.getDocument().replaceString(start, end, executor.getResult());
                            model.removeSelection();
                        })
        );
        executor.conduct();
    }
}
