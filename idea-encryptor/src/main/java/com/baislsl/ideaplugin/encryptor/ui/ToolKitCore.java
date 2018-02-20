package com.baislsl.ideaplugin.encryptor.ui;

import com.baislsl.ideaplugin.encryptor.core.EncryptManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.ui.EditorTextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolKitCore extends JComponent implements ActionListener, DocumentListener {
    private final static Logger LOG = LoggerFactory.getLogger(ToolKitCore.class);
    private EditorTextField plaintextField;
    private EditorTextField ciphertextField;
    private boolean isEncrypt;
    private MethodPanel methodPanel;
    private KeyInputPanel keyInputPanel;
    private EncryptManager manager = new EncryptManager();
    private JComponent methodSwitchPanel;

    private ActionListener methodSwitchListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            isEncrypt = "encrypt".equals(e.getActionCommand());
            ToolKitCore.this.actionPerformed(e);
        }
    };

    public ToolKitCore() {
        plaintextField = new EditorTextField();
        ciphertextField = new EditorTextField();
        methodPanel = new MethodPanel();
        keyInputPanel = new KeyInputPanel(manager);
        plaintextField.addDocumentListener(this);
        ciphertextField.addDocumentListener(this);
        methodPanel.addActionListener(this);
        methodPanel.addActionListener(keyInputPanel);
        keyInputPanel.addActionListener(this);

        plaintextField.setPreferredWidth(200);
        ciphertextField.setPreferredWidth(200);
        ciphertextField.setEnabled(false);

        ButtonGroup group = new ButtonGroup();
        JRadioButton encryptButton = new JRadioButton("encrypt");
        encryptButton.setActionCommand("encrypt");
        encryptButton.addActionListener(methodSwitchListener);
        JRadioButton decryptButton = new JRadioButton("decrypt");
        decryptButton.setActionCommand("decrypt");
        decryptButton.addActionListener(methodSwitchListener);
        group.add(encryptButton);
        group.add(decryptButton);
        encryptButton.setSelected(true);
        isEncrypt = true;
        methodSwitchPanel = new JPanel();
        methodSwitchPanel.setLayout(new GridLayout(2, 1));
        methodSwitchPanel.add(encryptButton);
        methodSwitchPanel.add(decryptButton);


        this.setLayout(new GridLayout(1, 3));

        JPanel configurePanel = new JPanel();
        configurePanel.setLayout(new GridLayout(2, 1));

        this.add(plaintextField);
        this.add(configurePanel);
        this.add(ciphertextField);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(methodPanel);
        panel.add(methodSwitchPanel);

        configurePanel.add(panel);
        configurePanel.add(keyInputPanel);

        this.setPreferredSize(new Dimension(600, 200));

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        LOG.info(e.toString());
        flesh();
    }

    @Override
    public void documentChanged(DocumentEvent event) {
        LOG.info(event.toString());
        flesh();
    }

    private void flesh() {
        manager.setEncodeMethod(methodPanel.getMethod());
        if (manager.isLegalKey(keyInputPanel.getPassword())) {
            manager.setKey(keyInputPanel.getPassword());
            String result = isEncrypt ? manager.encode(plaintextField.getText())
                                      : manager.decode(plaintextField.getText());
            if(!result.equals(ciphertextField.getText())) {
                WriteCommandAction.runWriteCommandAction(
                        ProjectManager.getInstance().getDefaultProject(),
                        () -> ciphertextField.setText(result)
                );
            }
        }
    }
}
