package com.baislsl.ideaplugin.encryptor.ui;

import com.baislsl.ideaplugin.encryptor.core.EncryptManager;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.ui.EditorTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolKitCore extends JComponent implements ActionListener, DocumentListener {
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
        }
    };

    public ToolKitCore() {
        plaintextField = new EditorTextField();
        ciphertextField = new EditorTextField();
        methodPanel = new MethodPanel();
        keyInputPanel = new KeyInputPanel();
        plaintextField.addDocumentListener(this);
        ciphertextField.addDocumentListener(this);
        methodPanel.addActionListener(this);
        keyInputPanel.addActionListener(this);


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

        this.add(plaintextField);
        this.add(ciphertextField);
        this.add(methodPanel);
        this.add(keyInputPanel);
        this.add(methodSwitchPanel);

        this.setSize(new Dimension(600, 400));
        plaintextField.setBounds(0, 0, 200, 200);
        methodPanel.setBounds(220, 0, 70, 80);
        methodSwitchPanel.setBounds(310, 0, 70, 80);
        keyInputPanel.setBounds(220, 100, 160, 60);
        ciphertextField.setBounds(400, 0, 200, 200);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        flesh();
    }

    @Override
    public void documentChanged(DocumentEvent event) {
        flesh();
    }

    private void flesh() {
        manager.setEncodeMethod(methodPanel.getMethod());
        if (manager.isLegalKey(keyInputPanel.getPassword())) {
            manager.setKey(keyInputPanel.getPassword());
            String result = isEncrypt ? manager.encode(plaintextField.getText())
                                      : manager.decode(plaintextField.getText());
            if(!result.equals(ciphertextField.getText())) {
                // TODO: use WriteCommandAction.runWriteCommandAction instead
                SwingUtilities.invokeLater(
                        () -> ciphertextField.setText(result)
                );
            }
        }
    }
}
