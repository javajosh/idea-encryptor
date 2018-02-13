package com.baislsl.ideaplugin.encryptor.core;

import com.baislsl.ideaplugin.encryptor.core.method.EncryptMethod;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EncryptManager {
    private String key;
    private EncryptMethod method;
    private static List<Encryptor> encryptors;

    static {
        encryptors = Arrays.stream(EncryptMethod.values())
                .map(EncryptMethod::getNewInstance)
                .collect(Collectors.toList());
    }

    public EncryptManager() {
        this.method = EncryptMethod.AES_ENCODER;
    }

    public boolean setKey(String key) {
        this.key = key;
        if (getEncoder() == null) {
            return true;
        }
        return getEncoder().isLegalKey(key);
    }

    public void setEncodeMethod(EncryptMethod method) {
        Objects.requireNonNull(method);
        this.method = method;
    }

    public String encode(String s) {
        return getEncoder().encode(s, key);
    }

    public String decode(String s) {
        return getEncoder().decode(s, key);
    }

    public Encryptor getEncoder() {
        return encryptors.get(method.ordinal());
    }

}
