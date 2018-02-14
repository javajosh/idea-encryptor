package com.baislsl.ideaplugin.encryptor.ui;

import javax.swing.*;

import static org.junit.Assert.*;

public class ToolKitCoreTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tool Kit Panel Test");
//        frame.setLayout(new FlowLayout());
        JComponent component = new ToolKitCore();
        frame.add(component);
        frame.setVisible(true);
        frame.pack();
        frame.setSize(700, 500);
    }
}