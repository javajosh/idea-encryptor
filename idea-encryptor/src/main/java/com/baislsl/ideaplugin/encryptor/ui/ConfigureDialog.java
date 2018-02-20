package com.baislsl.ideaplugin.encryptor.ui;

import com.baislsl.ideaplugin.encryptor.core.EncryptManager;
import com.baislsl.ideaplugin.encryptor.core.method.EncryptMethod;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

public class ConfigureDialog extends DialogWrapper {

    private ConfigurePanel configurePanel = new ConfigurePanel();

    public ConfigureDialog(Project project) {
        super(project, false);
        init();
        setTitle("Encrypt Tool");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return configurePanel;
    }

    public EncryptMethod getMethod() {
        return configurePanel.getMethod();
    }

    public String getKey() {
        return configurePanel.getKey();
    }

    @NotNull
    @Override
    protected Action[] createActions() {
        return new Action[]{
                new DialogWrapperExitAction("Convert", DialogWrapper.OK_EXIT_CODE),
                new DialogWrapperExitAction("No", DialogWrapper.CANCEL_EXIT_CODE)
        };
    }

    public void setMethod(EncryptMethod method) {
        configurePanel.setMethod(method);
    }

    static class ConfigurePanel extends JComponent implements KeyLegalDetector {
        private MethodPanel methodPanel;
        private KeyInputPanel keyInputPanel;
        private EncryptManager manager;

        public ConfigurePanel() {
            this.setLayout(new FlowLayout());
            methodPanel = new MethodPanel();
            manager = new EncryptManager();
            keyInputPanel = new KeyInputPanel(this);
            this.add(methodPanel);
            this.add(keyInputPanel);
            methodPanel.addActionListener(keyInputPanel);
        }

        public EncryptMethod getMethod() {
            return methodPanel.getMethod();
        }

        public void setMethod(EncryptMethod method) {
            methodPanel.setMethod(method);
        }

        public String getKey() {
            return keyInputPanel.getPassword();
        }

        @Override
        public boolean isLegalKey(String key) {
            manager.setEncodeMethod(getMethod());
            return manager.isLegalKey(key);
        }
    }


}

