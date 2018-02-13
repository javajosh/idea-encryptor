package com.baislsl.ideaplugin.encryptor.core.method;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class AESEncryptorTest {
    private final static Logger LOG = LoggerFactory.getLogger(AESEncryptorTest.class);

    @Test
    public void basicTest() throws Exception {
        AESEncryptor encryptor = new AESEncryptor();
        String plaintext = "This a a naive project by baislsl";
        String key = "0123456789abcdef";

        String ciphertext = encryptor.encode(plaintext, key);
        LOG.info("Cipher text = {}", ciphertext);
        String decodeText = encryptor.decode(ciphertext, key);
        assertEquals(plaintext, decodeText);
    }

    @Test
    public void isLegalText() {
        AESEncryptor encryptor = new AESEncryptor();
        assertTrue(encryptor.isLegalKey("1234567890abcdef"));
        assertFalse(encryptor.isLegalKey(null));
        assertFalse(encryptor.isLegalKey("12345678"));
    }
}