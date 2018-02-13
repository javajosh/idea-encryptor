package com.baislsl.ideaplugin.encryptor.action;

import com.baislsl.ideaplugin.encryptor.core.method.EncryptMethod;
import com.baislsl.ideaplugin.encryptor.ui.EncryptMethodPopup;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;

import java.awt.*;
import java.util.Objects;

public abstract class BaseTransferAction extends AnAction implements MethodReceiver {
    protected Document document;
    protected Project project;
    protected String key;
    protected EncryptMethod method;

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        project = anActionEvent.getProject();
        document = anActionEvent.getData(PlatformDataKeys.EDITOR).getDocument();
        Objects.requireNonNull(document);

        JBPopupFactory factory = JBPopupFactory.getInstance();
        EncryptMethodPopup item = new EncryptMethodPopup(project, document, this);
        ListPopup popup = factory.createListPopup(item);
        popup.show(new Panel());
    }

    @Override
    public void accept(EncryptMethod method) {
        this.method = method;
        this.key = getInputKey();
        conductTransfer();
    }

    private String getInputKey() {
        return Messages.showInputDialog(project,
                "Input your key",
                "Key",
                Messages.getQuestionIcon());
    }

    protected abstract void conductTransfer();
}
