package com.baislsl.ideaplugin.encryptor.ui;

import com.intellij.ui.JBColor;
import com.intellij.ui.components.JBPasswordField;
import com.intellij.ui.components.JBTextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.*;

public class KeyInputPanel extends JComponent implements ActionListener {
    private final static Logger LOG = LoggerFactory.getLogger(KeyInputPanel.class);
    private final static KeyLegalDetector defaultDetector = s -> true;
    private final static Color LEGAL_COLOR = JBColor.WHITE;
    private final static Color ILLEGAL_COLOR = JBColor.RED;
    private final static String HINT = "Input key； ";
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
        hintText.setText(HINT);
        this.add(hintText);
        this.add(passwordField);
        this.setPreferredSize(new Dimension(200, 100));

        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                KeyInputPanel.this.actionPerformed(null);
            }
        });
    }

    public void setHintText(String text) {
        hintText.setText(text);
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        LOG.info("password test changed.");
        if (detector.isLegalKey(getPassword())) {
            hintText.setForeground(LEGAL_COLOR);
            hintText.setText(HINT + "legal");
        } else {
            hintText.setForeground(ILLEGAL_COLOR);
            hintText.setText(HINT + "illegal");
        }

    }

    public synchronized void addActionListener(ActionListener listener) {
        passwordField.addActionListener(listener);
    }

    public synchronized void removeActionListener(ActionListener listener) {
        passwordField.removeActionListener(listener);
    }

    public void setDetector(KeyLegalDetector detector) {
        this.detector = detector;
    }
}
