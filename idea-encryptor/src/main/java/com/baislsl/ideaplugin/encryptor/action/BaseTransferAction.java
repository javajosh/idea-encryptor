package com.baislsl.ideaplugin.encryptor.action;

import com.baislsl.ideaplugin.encryptor.core.method.EncryptMethod;
import com.baislsl.ideaplugin.encryptor.ui.EncryptMethodPopup;
import com.baislsl.ideaplugin.encryptor.ui.KeyQueryDialog;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Objects;

public abstract class BaseTransferAction extends AnAction implements MethodReceiver {
    protected Document document;
    protected Project project;
    protected String key;
    protected EncryptMethod method;

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        conduct(anActionEvent.getProject(), anActionEvent.getData(PlatformDataKeys.EDITOR).getDocument());
    }

    public void conduct(Project project, Document document) {
        this.project = project;
        this.document = document;
        JBPopupFactory factory = JBPopupFactory.getInstance();
        EncryptMethodPopup item = new EncryptMethodPopup(project, document, this);
        ListPopup popup = factory.createListPopup(item);
        popup.show(new Panel());
    }

    @Override
    public void accept(@Nullable EncryptMethod method) {
        if(method == null) return;
        this.method = method;
        this.key = new KeyQueryDialog(project, method).getKey();
        conductTransfer();
    }


    protected abstract void conductTransfer();

    public void setProject(Project project) {
        this.project = project;
    }

    public void setMethod(EncryptMethod method) {
        this.method = method;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
