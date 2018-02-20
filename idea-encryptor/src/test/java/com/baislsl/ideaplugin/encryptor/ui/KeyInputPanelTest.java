package com.baislsl.ideaplugin.encryptor.ui;

import javax.swing.*;

import java.awt.*;

import static org.junit.Assert.*;

public class KeyInputPanelTest {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Key input Panel Test");
        frame.setLayout(new FlowLayout());
        JComponent component = new KeyInputPanel();
        frame.add(component);
        frame.setVisible(true);
        frame.pack();
        frame.setSize(700, 500);
    }

}