package com.mobilebackendfinancials.financial.util;


import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;


public class EncryptionAsymmetricUtils {

    public final static String RSA = "RSA";

    /**
     * -----BEGIN RSA PRIVATE KEY-----
     * -----END RSA PRIVATE KEY-----
     */
    private static final String rsaFilePatternRemove = "-+(BEGIN|END)\\s*RSA\\s*(PRIVATE|PUBLIC)\\s*KEY\\s*-+";


    /**
     * Generates a public and private key pair
     *
     * @return the generated key pair
     * @throws Exception exception
     */
    public static KeyPair generateRSAKeyPair() throws Exception {
        SecureRandom secureRandom = new SecureRandom();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);

        keyPairGenerator.initialize(2048, secureRandom);

        return keyPairGenerator.generateKeyPair();
    }


    /**
     * Encrypts a plaintext using the public key
     *
     * @param plainText: the text to encrypt
     * @param publicKey: the public key
     * @return the encrypted text
     * @throws Exception exception
     */
    public static byte[] doRSAEncryption(String plainText, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);

        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        return cipher.doFinal(plainText.getBytes());
    }


    /**
     * Decrypts a cipher text using the private key
     *
     * @param cipherText: the cipher text
     * @param privateKey: the private key
     * @return the plain text
     * @throws Exception exception
     */
    public static String doRSADecryption(byte[] cipherText, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);

        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] result = cipher.doFinal(cipherText);

        return new String(result);
    }


    /**
     * Gets the private key object from  a string
     *
     * @param privateKey: the private key in string
     * @return the private key object
     * @throws Exception exception
     */
    public static PrivateKey getPrivateKeyFromString(String privateKey) throws Exception {
        byte[] clear = base64Decode(privateKey.replaceAll(rsaFilePatternRemove, "").replaceAll("\\n", ""));
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(clear);
        KeyFactory fact = KeyFactory.getInstance(RSA);
        PrivateKey priv = fact.generatePrivate(keySpec);
        Arrays.fill(clear, (byte) 0);
        return priv;
    }


    /**
     * Gets the public key from a string
     *
     * @param publicKey: the public key string
     * @return the public key object
     * @throws Exception exception
     */
    public static PublicKey getPublicKeyFromString(String publicKey) throws Exception {
        byte[] data = base64Decode(publicKey.replaceAll(rsaFilePatternRemove, "").replaceAll("\\n", ""));
        X509EncodedKeySpec spec = new X509EncodedKeySpec(data);
        KeyFactory fact = KeyFactory.getInstance(RSA);
        return fact.generatePublic(spec);
    }


    /**
     * gets a private key as string
     *
     * @param privateKey: the private key object
     * @return the private key as string
     * @throws Exception exception
     */
    public static String convertPrivateKeyToString(PrivateKey privateKey) throws Exception {
        KeyFactory fact = KeyFactory.getInstance(RSA);
        PKCS8EncodedKeySpec spec = fact.getKeySpec(privateKey,
                PKCS8EncodedKeySpec.class);
        byte[] packed = spec.getEncoded();
        String key64 = base64Encode(packed);

        Arrays.fill(packed, (byte) 0);
        return key64;
    }


    /**
     * Gets a public key as string
     *
     * @param publicKey: the public key object
     * @return the public key as string
     * @throws Exception exception
     */
    public static String convertPublicKeyToString(PublicKey publicKey) throws Exception {
        KeyFactory fact = KeyFactory.getInstance(RSA);
        X509EncodedKeySpec spec = fact.getKeySpec(publicKey,
                X509EncodedKeySpec.class);
        return base64Encode(spec.getEncoded());
    }

    /**
     * Encodes an array of bytes as string
     *
     * @param bytes: the byte array
     * @return the string equivalent
     */
    public static String base64Encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * Decodes a string to byte equivalent
     *
     * @param encoded: the encoded string
     * @return the byte equivalent of the encoded string
     */
    public static byte[] base64Decode(String encoded) {
        return Base64.getDecoder().decode(encoded);
    }

//    public static void main(String[] args) throws Exception {
//
//        //KeyPair keyPair = generateRSAKeyPair();
//        String publicKey = "-----BEGIN RSA PUBLIC KEY-----\n" +
//                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnUr5f5eGwmPo9gVfOBA9yKE1ip1ca4LPEyWZVi2kPPdoxQBkBWSPCv51sJVMvVthXNgIxf34TyElsY2JE/mGqrA0lCXgceyqbTJkwM9z3zD/I9iMfgsfD1409k3dKpyE9gZUZFyKz4wfFd84nBGK+UcDo9EvaeEO94/ACKTjO4AZrR1+MqbA3KC3lLAa9DxQNAXPWgDBw4kbFUc7Uie7PiI2PKDbI/Sse0qadrFi8nSqjGmhUsmIVe6b0nE9bF6e3mDkvligdW5m4dSG8ZKwUeZxFvUpLnZvpZx2kfxr42Hph6h2zfsWCxfRh3aAuSWtt7mkKPOWOrrOAK5SJ5fJpwIDAQAB\n" +
//                "-----END RSA PUBLIC KEY-----";
//        String privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" +
//                "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCdSvl/l4bCY+j2BV84ED3IoTWKnVxrgs8TJZlWLaQ892jFAGQFZI8K/nWwlUy9W2Fc2AjF/fhPISWxjYkT+YaqsDSUJeBx7KptMmTAz3PfMP8j2Ix+Cx8PXjT2Td0qnIT2BlRkXIrPjB8V3zicEYr5RwOj0S9p4Q73j8AIpOM7gBmtHX4ypsDcoLeUsBr0PFA0Bc9aAMHDiRsVRztSJ7s+IjY8oNsj9Kx7Spp2sWLydKqMaaFSyYhV7pvScT1sXp7eYOS+WKB1bmbh1IbxkrBR5nEW9Skudm+lnHaR/GvjYemHqHbN+xYLF9GHdoC5Ja23uaQo85Y6us4ArlInl8mnAgMBAAECggEAWgYgOEN8UGOoYK6AafD5u4twvm4ECFBsvhOl6CnlNzOPFjl8BY4hRHSQ3UkGC9ZyKhZHJzKNXJSnIzDThMgqqCSDY0suY2XFwQu3mJbjRZmgXV7CQh2RYFVV0KaN76Z3fuvufyZsGA+ldvo0RzbPKI7ZaXf/QAUccNooWYott85aQfOalYgW8gCGoRy6VFkXRboqtX1Sv+1IJAmHvDDnm7xzeWN0sF/G0XIriQ/y7sAyA2NkS7osL/iiHAlPkRKs9alAJ5Jxz+1JC+ugnvxCbMfFDcbUE4xVIWMdDUcyYNsjCQrLglqZEaaHC/yaKDradKgQpKTEx2sfkJyJgJlh2QKBgQDVXMn1pp4ApbUop7QZzx9RUxWhOitbJYz1V9fj0whmPbC8/4uZiUBjJ7JbclZj/bFGtA0zj2XmWEB/uD7Txtutv1oguNT/EfxI1cLf0Uj/q7kvdmiHUaFFyO2PehRwbj+HbalWqvXkV1oEpz6yYU8NleiPVTT/kjiJr8dqMS/HHQKBgQC8ucYg+C3SNiVM8Z278gun41nsl7/HC9ckQ3sRmLY92osgn5+O4OIBiFTwFz5raGmYQCWAymUyQCZ5tbkKATl9nnLCZ7xkPZDyOqNrCM3CJDMZhsBk8Job1qlwj3jfhbjxr26G1rx4eBmpEXhEdwyWGkdBMtk0B0nn6C2UGHwEkwKBgQCh17CaVbikZQ31uMA0k5f96DgQBD++88zojamFdx5373OQ6YbLAAGHNSVlmGVPk7BVIV6iLbPt9iN2vhtqKP2Z3fxPteRbVxk3Zm5buDTXOztNdVP00Uaz2KXan4/BeN9Xqyc4RpYejuQJg5NbW9TLSVsO3mNFOXUJe/yX4JeNQQKBgCh4ljpfvkPEBL+5WKADSeiY4qHP8PpYWayXI/8kBoDBKvC2CltzckPt3nkWIvaZj3ts1h4GSSk7hIS5KZMxrAh6QBXcdRPx9/FaDc1YN/eYF7UzpnJ+/i1WHUPcIImefYimnEciKYyUBLyy2F2mQfa2b+1VYCq+HWUz7wJBNectAoGALC/wSmfdYpJFrDiOC3RQjggR0o2dHzRZDT2TzBi8bbZcdSsBydV4BCntVvDtHw4fw4CDBW+YmcMwRkdDbwkN99YihYmniZtOYlE0TynGr+HB9NsDEM3EpUXOfi4/TWkZ17YfSu8DQDF//RIrKemIpxpKI6wRjJqrbvwABaHkUYw=\n" +
//                "-----END RSA PRIVATE KEY-----";
//
//        System.out.println("Public: " + publicKey + "\nPrivate: " + privateKey);
//
//        String text = "I am here";
//        byte[] encryption = doRSAEncryption(text, getPublicKeyFromString(publicKey));
//        String enc = base64Encode(encryption);
//        System.out.println("EncryptionAsymmetricUtils: " + enc);
//
//        System.out.println("Decryption: " + doRSADecryption(base64Decode(enc), getPrivateKeyFromString(privateKey)));
//
//
//        System.out.println(base64Encode(doRSAEncryption("string", getPublicKeyFromString(publicKey))));
//    }
}
