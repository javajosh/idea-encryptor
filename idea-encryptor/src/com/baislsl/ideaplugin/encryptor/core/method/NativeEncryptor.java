package com.baislsl.ideaplugin.encryptor.core.method;

import com.baislsl.ideaplugin.encryptor.core.Encryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NativeEncryptor implements Encryptor {
    private final static Logger LOG = LoggerFactory.getLogger(NativeEncryptor.class);
    private final static String HEADER = "baislsl";

    @Override
    public boolean detect(String ciphertext) {
        return HEADER.equals(ciphertext.substring(0, HEADER.length()));
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
