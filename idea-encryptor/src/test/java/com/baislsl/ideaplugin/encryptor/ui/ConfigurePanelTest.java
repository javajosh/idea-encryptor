package com.baislsl.ideaplugin.encryptor.ui;

import javax.swing.*;

public class ConfigurePanelTest {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Configure Panel Test");
//        frame.setLayout(new FlowLayout());
        JComponent component = new ConfigureDialog.ConfigurePanel();
        frame.add(component);
        frame.setVisible(true);
        frame.pack();
    }

}