package com.baislsl.ideaplugin.encryptor.ui;

import com.baislsl.ideaplugin.encryptor.core.EncryptManager;
import com.baislsl.ideaplugin.encryptor.core.method.EncryptMethod;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.popup.PopupStep;
import com.intellij.openapi.ui.popup.util.BaseListPopupStep;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class EncryptMethodPopup extends BaseListPopupStep<EncryptMethod> {
    private EncryptMethod method;
    private Project project;
    private Document document;

    public EncryptMethodPopup(Project project, Document document) {
        super("Encrpt Or Decrypt Method", EncryptMethod.values());
        this.project = project;
        this.document = document;
    }

    @NotNull
    @Override
    public String getTextFor(EncryptMethod value) {
        return value.name();
    }

    @Override
    public PopupStep onChosen(EncryptMethod selectedValue, boolean finalChoice) {
        if (finalChoice) {
            onCreateKeyInputMessages(selectedValue);
        }
        return super.onChosen(selectedValue, finalChoice);
    }

    public EncryptMethod getEncryptMethod() {
        return method;
    }

    private void onCreateKeyInputMessages(EncryptMethod method) {
        EncryptManager encryptManager = new EncryptManager();
        Objects.requireNonNull(document);
        String key = Messages.showInputDialog(project,
                "Input your key",
                "Key",
                Messages.getQuestionIcon());
        encryptManager.setKey(key);
        document.setText(encryptManager.encode(document.getText()));
    }
}
