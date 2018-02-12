package com.baislsl.ideaplugin.encryptor.core.method;

import com.baislsl.ideaplugin.encryptor.core.Encryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum EncryptMethod {
    NATIVE_ENCODER(NativeEncryptor.class);


    private Class<?> clazz;
    private final static Logger LOG = LoggerFactory.getLogger(EncryptMethod.class);

    EncryptMethod(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Encryptor getNewInstance() {
        try {
            return (Encryptor) clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            LOG.error("Error constructin new encoder", e);
        }
        return null;

    }

}
