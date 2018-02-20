package com.baislsl.ideaplugin.encryptor.ui;

import org.jetbrains.annotations.Nullable;

public interface KeyLegalDetector {

    boolean isLegalKey(String key);

    @Nullable
    default String getHint() {
        return null;
    }

}
