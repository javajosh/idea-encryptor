package com.baislsl.ideaplugin.encryptor.core.method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum EncryptMethod {
    NAIVE(NativeEncryptor.class, ""),
    AES(AESEncryptor.class, "Must be 16 words");


    private Class<?> clazz;
    private String hint;
    private final static Logger LOG = LoggerFactory.getLogger(EncryptMethod.class);

    EncryptMethod(Class<?> clazz, String hint) {
        this.clazz = clazz;
        this.hint = hint;
    }

    public Encryptor getNewInstance() {
        try {
            return (Encryptor) clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            LOG.error("Error constructing new encoder", e);
        }
        return null;

    }

    public String getHint() {
        return hint;
    }
}
