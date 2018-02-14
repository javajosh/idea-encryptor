package com.baislsl.ideaplugin.encryptor.ui;

import com.intellij.ui.JBColor;
import com.intellij.ui.components.JBPasswordField;
import com.intellij.ui.components.JBTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KeyInputPanel extends JComponent implements ActionListener {
    private final static KeyLegalDetector defaultDetector = s -> true;
    private final static Color LEGAL_COLOR = JBColor.WHITE;
    private final static Color ILLEGAL_COLOR = JBColor.RED;
    private JBPasswordField passwordField;
    private JTextPane hintText;
    private KeyLegalDetector detector;

    public KeyInputPanel() {
        this(defaultDetector);
    }

    public KeyInputPanel(KeyLegalDetector detector) {
        super();
        this.setLayout(new GridLayout(2, 1));
        this.detector = detector;
        passwordField = new JBPasswordField();
        hintText = new JTextPane();
        hintText.setEnabled(false);
        hintText.setText("Input key");
        this.add(hintText);
        this.add(passwordField);
        this.setPreferredSize(new Dimension(200, 100));

        passwordField.addActionListener(this);
    }

    public void setHintText(String text) {
        hintText.setText(text);
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (detector.isLegalKey(getPassword())) {
            this.hintText.setForeground(LEGAL_COLOR);
        } else {
            this.hintText.setForeground(ILLEGAL_COLOR);
        }

    }

    public synchronized void addActionListener(ActionListener listener) {
        passwordField.addActionListener(listener);
    }

    public synchronized void removeActionListener(ActionListener listener) {
        passwordField.removeActionListener(listener);
    }
}
