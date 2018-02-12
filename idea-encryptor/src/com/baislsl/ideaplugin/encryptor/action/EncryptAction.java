package com.baislsl.ideaplugin.encryptor.action;

import com.baislsl.ideaplugin.encryptor.core.EncryptManager;
import com.baislsl.ideaplugin.encryptor.ui.EncryptMethodPopup;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.popup.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class EncryptAction extends AnAction {
    private final static Logger LOG = LoggerFactory.getLogger(EncryptAction.class);

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        Document document = e.getData(PlatformDataKeys.EDITOR).getDocument();
        Objects.requireNonNull(document);
        LOG.info(document.getText());

        JBPopupFactory factory = JBPopupFactory.getInstance();
        EncryptMethodPopup item = new EncryptMethodPopup(project, document);
        ListPopup popup = factory.createListPopup(item);
        popup.show(new Panel());
    }

}