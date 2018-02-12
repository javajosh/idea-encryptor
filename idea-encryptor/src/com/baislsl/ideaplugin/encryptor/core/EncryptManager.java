package com.baislsl.ideaplugin.encryptor.core;

import com.baislsl.ideaplugin.encryptor.core.method.EncryptMethod;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EncryptManager {
    private String password;
    private EncryptMethod method;
    private static List<Encryptor> encryptors;

    static {
        encryptors = Arrays.stream(EncryptMethod.values())
                .map(EncryptMethod::getNewInstance)
                .collect(Collectors.toList());
    }

    public EncryptManager() {
        this.method = EncryptMethod.NATIVE_ENCODER;
    }

    public boolean setPassword(String password) {
        this.password = password;
        if (getEncoder() == null) {
            return true;
        }
        return getEncoder().isLegalPassword(password);
    }

    public void setEncodeMethod(EncryptMethod method) {
        Objects.requireNonNull(method);
        this.method = method;
    }

    public String encode(String s) {
        return getEncoder().encode(s, password);
    }

    public String decode(String s) {
        return getEncoder().decode(s, password);
    }

    public Encryptor getEncoder() {
        return encryptors.get(method.ordinal());
    }

}
