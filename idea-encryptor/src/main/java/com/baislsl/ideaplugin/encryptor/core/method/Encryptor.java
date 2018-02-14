package com.baislsl.ideaplugin.encryptor.core.method;

import com.baislsl.ideaplugin.encryptor.ui.KeyLegalDetector;

public interface Encryptor extends KeyLegalDetector {

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

}
