package com.baislsl.ideaplugin.encryptor.action;

import com.baislsl.ideaplugin.encryptor.core.EncryptManager;
import com.baislsl.ideaplugin.encryptor.core.method.EncryptMethod;
import com.baislsl.ideaplugin.encryptor.ui.EncryptMethodPopup;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.popup.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class EncryptAction extends AnAction implements MethodReceiver {
    private final static Logger LOG = LoggerFactory.getLogger(EncryptAction.class);
    private Document document;
    private Project project;

    @Override
    public void actionPerformed(AnActionEvent e) {
        project = e.getProject();
        document = e.getData(PlatformDataKeys.EDITOR).getDocument();
        Objects.requireNonNull(document);
        LOG.info(document.getText());

        JBPopupFactory factory = JBPopupFactory.getInstance();
        EncryptMethodPopup item = new EncryptMethodPopup(project, document, this);
        ListPopup popup = factory.createListPopup(item);
        popup.show(new Panel());
    }


    @Override
    public void accept(EncryptMethod method) {
        EncryptManager encryptManager = new EncryptManager();
        Objects.requireNonNull(document);
        String key = Messages.showInputDialog(project,
                "Input your key",
                "Key",
                Messages.getQuestionIcon());
        encryptManager.setKey(key);
        String text = encryptManager.encode(document.getText());

        Runnable task = () -> {
            document.setText(text);
        };

        WriteCommandAction.runWriteCommandAction(project, task);
    }
}
