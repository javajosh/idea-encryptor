package com.baislsl.ideaplugin.encryptor.detect;

import com.baislsl.ideaplugin.encryptor.action.DecryptAction;
import com.baislsl.ideaplugin.encryptor.core.EncryptManager;
import com.baislsl.ideaplugin.encryptor.core.method.EncryptMethod;
import com.baislsl.ideaplugin.encryptor.ui.DetectConfirmDialog;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.messages.MessageBus;
import com.intellij.util.messages.MessageBusConnection;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FileOpenDetector implements ProjectComponent {
    private final static Logger LOG = LoggerFactory.getLogger(FileOpenDetector.class);
    private MessageBusConnection connection;
    private static EncryptManager manager = new EncryptManager();

    @Override
    public void projectOpened() {
        MessageBus messageBus = ApplicationManager.getApplication().getMessageBus();
        connection = messageBus.connect();
        connection.subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, new FileEditorManagerListener() {
            @Override
            public void fileOpened(@NotNull FileEditorManager source, @NotNull VirtualFile file) {
                Document document = FileDocumentManager.getInstance().getDocument(file);
                Project project = ProjectManager.getInstance().getDefaultProject();
                EncryptMethod method = manager.detect(document.getText());
                if (method != null) {
                    int exitCode = openConfirmDialog(file.getPath(), project);
                    LOG.debug("Exit Code = {}", exitCode);
                    if (exitCode == DetectConfirmDialog.USE_EXIT_CODE) {
                        LOG.info("detect" + method);
                        openDecryptWindow(method, project,  document);
                    }

                }
            }
        });
    }

    @Override
    public void projectClosed() {
        connection.disconnect();
    }

    private int openConfirmDialog(String filePath, Project project) {
        DetectConfirmDialog dialog = new DetectConfirmDialog(
                project,
                "The file " + filePath + " seems to be encrypt by Encrypt Tool. Use" +
                        "Encrypt Tool to decrypt it ?");
        dialog.show();
        return dialog.getExitCode();
    }

    private void openDecryptWindow(EncryptMethod method, Project project, Document document) {
        DecryptAction action = new DecryptAction();
        action.setDocument(document);
        action.setProject(project);
        action.accept(method);
    }
}
