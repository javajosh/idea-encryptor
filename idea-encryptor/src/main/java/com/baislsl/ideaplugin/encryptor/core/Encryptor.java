package com.baislsl.ideaplugin.encryptor.core;

public interface Encryptor {

    /**
     *
     * @param plaintext
     * @param key
     * @return
     */
    String encode(String plaintext, String key);


    /**
     *
     * @param ciphertext
     * @param key
     * @return
     */
    String decode(String ciphertext, String key);

    boolean detect(String ciphertext);

    boolean isLegalKey(String key);

}
