package com.baislsl.ideaplugin.encryptor.ui;

import com.baislsl.ideaplugin.encryptor.core.method.EncryptMethod;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MethodPanel extends JComponent implements ActionListener {
    private EncryptMethod method;
    private ButtonGroup group;
    private java.util.List<JRadioButton> buttonList = new ArrayList<>();

    public MethodPanel() {
        this.setLayout(new GridLayout(EncryptMethod.values().length, 1));
        group = new ButtonGroup();
        for (EncryptMethod method : EncryptMethod.values()) {
            JRadioButton button = new JRadioButton(method.name());
            button.setActionCommand(method.name());
            button.addActionListener(this);
            this.add(button);
            group.add(button);
            buttonList.add(button);
        }

        // set default selection
        buttonList.get(0).setSelected(true);
    }


    public EncryptMethod getMethod() {
        return method;
    }

    public void setMethod(EncryptMethod method) {
        group.clearSelection();
        buttonList.get(method.ordinal()).setSelected(true);
        this.method = method;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        this.method = EncryptMethod.valueOf(e.getActionCommand());

    }
}
