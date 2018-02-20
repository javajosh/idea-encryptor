package com.baislsl.ideaplugin.encryptor.core.method;

import com.baislsl.ideaplugin.encryptor.ui.KeyLegalDetector;
import org.jetbrains.annotations.Nullable;

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

    @Nullable
    default String getHint(){
        return null;
    }

}
