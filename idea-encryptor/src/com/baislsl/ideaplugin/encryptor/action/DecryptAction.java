package com.baislsl.ideaplugin.encryptor.action;

import com.baislsl.ideaplugin.encryptor.core.EncryptManager;

import com.intellij.openapi.command.WriteCommandAction;


public class DecryptAction extends BaseTransferAction {
    @Override
    protected void conductTransfer() {
        EncryptManager encryptManager = new EncryptManager();
        encryptManager.setEncodeMethod(method);
        encryptManager.setKey(key);
        String text = encryptManager.decode(document.getText());
        WriteCommandAction.runWriteCommandAction(project, () -> document.setText(text));

    }
}
