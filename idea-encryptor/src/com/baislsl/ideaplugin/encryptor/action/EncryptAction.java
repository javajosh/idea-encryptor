package com.baislsl.ideaplugin.encryptor.action;

import com.baislsl.ideaplugin.encryptor.core.EncryptManager;

import com.intellij.openapi.command.WriteCommandAction;


public class EncryptAction extends BaseTransferAction {

    @Override
    protected void conductTransfer() {
        EncryptManager encryptManager = new EncryptManager();
        encryptManager.setEncodeMethod(method);
        encryptManager.setKey(key);
        String text = encryptManager.encode(document.getText());
        WriteCommandAction.runWriteCommandAction(project, () -> document.setText(text));

    }
}
