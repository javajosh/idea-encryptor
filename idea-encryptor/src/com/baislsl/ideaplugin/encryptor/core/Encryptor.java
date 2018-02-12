package com.baislsl.ideaplugin.encryptor.core;

public interface Encryptor {

    /**
     *
     * @param plaintext
     * @param password
     * @return
     */
    String encode(String plaintext, String password);


    /**
     *
     * @param ciphertext
     * @param password
     * @return
     */
    String decode(String ciphertext, String password);

    boolean detect(String ciphertext);

    boolean isLegalPassword(String password);

}
