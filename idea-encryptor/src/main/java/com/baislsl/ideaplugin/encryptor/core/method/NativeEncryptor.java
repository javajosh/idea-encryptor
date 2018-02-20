package com.baislsl.ideaplugin.encryptor.core.method;


import com.intellij.openapi.diagnostic.Logger;
import org.jetbrains.annotations.Nullable;

public class NativeEncryptor implements Encryptor {
    private final static Logger LOG = Logger.getInstance(NativeEncryptor.class);
    private final static String HEADER = "baislsl";
    private final static String HINT = null;

    @Override
    public boolean detect(String ciphertext) {
        return ciphertext.length() >= HEADER.length() && HEADER.equals(ciphertext.substring(0, HEADER.length()));
    }

    @Override
    public String encode(String plaintext, String key) {
        return HEADER + plaintext;
    }

    @Override
    public String decode(String ciphertext, String key) {
        return ciphertext.substring(HEADER.length());
    }

    @Override
    public boolean isLegalKey(String key) {
        return true;
    }
}
