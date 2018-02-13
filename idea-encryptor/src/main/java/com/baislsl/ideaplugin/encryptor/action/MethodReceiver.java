package com.baislsl.ideaplugin.encryptor.action;

import com.baislsl.ideaplugin.encryptor.core.method.EncryptMethod;

public interface MethodReceiver {
    void accept(EncryptMethod method);
}
