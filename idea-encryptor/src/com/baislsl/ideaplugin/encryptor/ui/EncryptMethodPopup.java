package com.baislsl.ideaplugin.encryptor.ui;

import com.baislsl.ideaplugin.encryptor.action.MethodReceiver;
import com.baislsl.ideaplugin.encryptor.core.EncryptManager;
import com.baislsl.ideaplugin.encryptor.core.method.EncryptMethod;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.popup.PopupStep;
import com.intellij.openapi.ui.popup.util.BaseListPopupStep;
import org.jetbrains.annotations.NotNull;

public class EncryptMethodPopup extends BaseListPopupStep<EncryptMethod> {
    private EncryptMethod method;
    private Project project;
    private Document document;
    private MethodReceiver receiver;

    public EncryptMethodPopup(Project project, Document document, MethodReceiver receiver) {
        super("Encrpt Or Decrypt Method", EncryptMethod.values());
        this.project = project;
        this.document = document;
        this.receiver = receiver;
    }

    @NotNull
    @Override
    public String getTextFor(EncryptMethod value) {
        return value.name();
    }

    @Override
    public PopupStep onChosen(EncryptMethod selectedValue, boolean finalChoice) {
        method = selectedValue;
        return super.onChosen(selectedValue, finalChoice);
    }

    public EncryptMethod getEncryptMethod() {
        return method;
    }

    @Override
    public Runnable getFinalRunnable() {
        return () -> receiver.accept(method);
    }
}
