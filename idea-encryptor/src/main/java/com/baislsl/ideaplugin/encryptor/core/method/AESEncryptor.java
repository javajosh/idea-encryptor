package com.baislsl.ideaplugin.encryptor.core.method;

import com.baislsl.ideaplugin.encryptor.core.Encryptor;
import org.apache.commons.net.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESEncryptor implements Encryptor {
    private final static Logger LOG = LoggerFactory.getLogger(AESEncryptor.class);
    private final static String ENCRYPT_VECTOR = "plugin@baislsl--";
    private final static String CIPHER_METHOD = "AES/CBC/PKCS5PADDING";

    @Override
    public String encode(String plaintext, String key) {
        byte[] raw = key.getBytes();
        SecretKeySpec keySpec = new SecretKeySpec(raw, "AES");

        try {
            Cipher cipher = Cipher.getInstance(CIPHER_METHOD);

            IvParameterSpec iv = new IvParameterSpec(ENCRYPT_VECTOR.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);
            byte[] encrypted = cipher.doFinal(plaintext.getBytes());

            return new BASE64Encoder().encode(encrypted);
        } catch (Exception e) {
            LOG.error("encrypt error", e);
            return plaintext;
        }
    }

    @Override
    public String decode(String ciphertext, String key) {
        try {
            IvParameterSpec iv = new IvParameterSpec(ENCRYPT_VECTOR.getBytes("UTF-8"));
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance(CIPHER_METHOD);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);
            byte[] plaintext = cipher.doFinal(Base64.decodeBase64(ciphertext));
            return new String(plaintext);
        } catch (Exception e) {
            LOG.error("decrypt error", e);
            return ciphertext;
        }
    }

    @Override
    public boolean detect(String ciphertext) {
        return false;
    }

    @Override
    public boolean isLegalKey(String key) {
        return key != null && key.length() == 16;
    }
}
