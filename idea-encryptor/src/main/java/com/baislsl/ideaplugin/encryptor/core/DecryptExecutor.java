package com.baislsl.ideaplugin.encryptor.core;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;


public class DecryptExecutor extends BaseTransferExecutor {

    public DecryptExecutor(Project project, Document document) {
        super(project, document);
    }

    @Override
    protected void conductTransfer() {
        EncryptManager encryptManager = new EncryptManager();
        encryptManager.setEncodeMethod(method);
        encryptManager.setKey(key);
        String text = encryptManager.decode(document.getText());
        WriteCommandAction.runWriteCommandAction(project, () -> document.setText(text));
    }
}
