package com.mobilebackendfinancial.financial.util;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@RequiredArgsConstructor
@Component
public class JEncrypt {
    private static final String ENCRYPTION_ALGORITHM = "AES/GCM/NoPadding";
//    private static final String ENCRYPTION_ALGORITHM = "AES/GCM/PKCS5Padding";

    private static final int TAG_LENGTH_BIT = 128;
    private static final int IV_LENGTH_BYTE = 12;
    private static final int SALT_LENGTH_BYTE = 16;
    private static final Charset UTF_8 = StandardCharsets.UTF_8;
    private static final AesUtil aesUtil = new AesUtil();
    private static final String key = "rbJVr8j";


    public static void main(String[] args) throws Exception {
        JEncrypt jEncrypt = new JEncrypt();
//        String var = "O0racle_123#Obdxuat";
        String var = "bamobile";
        String enc = jEncrypt.encrypt(var);
        log.info("ENCRYPTED......." + enc);
        log.info("DECRYPTED......." + jEncrypt.decrypt("FBE0FF5YWD9QYlgLF0kSXTEEF1IgOlISMj8UKvoHpJHcr+005jN0F407QDPhb+bH0At38g=="));
    }


    public String encrypt(String text) throws Exception {
        log.info("Inside Encryption");
        byte[] pText = text.getBytes(UTF_8);
        byte[] salt = aesUtil.getSaltRandomByte(SALT_LENGTH_BYTE);
        byte[] iv = aesUtil.getSaltRandomByte(IV_LENGTH_BYTE);
        SecretKey aesKeyFromPassword = aesUtil.getAESKeyFromPassword(key.toCharArray(), salt);
        Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, aesKeyFromPassword, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
        byte[] cipherText = cipher.doFinal(pText);
        byte[] cipherTextWithIvSalt = ByteBuffer.allocate(iv.length + salt.length + cipherText.length)
                .put(iv)
                .put(salt)
                .put(cipherText)
                .array();
        return Base64.getEncoder().encodeToString(cipherTextWithIvSalt);

    }

    public String decrypt(String cText) {
        log.info("Inside Decryption");
        try {
            byte[] decode = Base64.getDecoder().decode(cText.getBytes(UTF_8));
            ByteBuffer bb = ByteBuffer.wrap(decode);
            byte[] iv = new byte[IV_LENGTH_BYTE];
            bb.get(iv);
            byte[] salt = new byte[SALT_LENGTH_BYTE];
            bb.get(salt);
            byte[] cipherText = new byte[bb.remaining()];
            bb.get(cipherText);
            SecretKey aesKeyFromPassword = aesUtil.getAESKeyFromPassword(key.toCharArray(), salt);
            Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, aesKeyFromPassword, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
            byte[] plainText = cipher.doFinal(cipherText);
            return new String(plainText, UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Exception @:::" + e.getMessage());

            return null;
        }


    }

}