package com.example.passwordencryptionap;

import android.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
/*
References:
https://stackoverflow.com/questions/22549251/encrypt-and-decrypt-using-aes-algorithms?rq=3
https://stackoverflow.com/questions/36895793/text-encryption-decryption-methods-java
https://github.com/teja156/python-password-manager
*/
public class PM_EncryptionActivity {
    //method to generates AES 256 bits key
    public static SecretKey generateKey() throws Exception {
        try{
            // use of algorithm
            KeyGenerator Generator = KeyGenerator.getInstance("AES");
            // key length in bytes
            Generator.init(256);//generates a key
            return Generator.generateKey();// returns generated key
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //data will be the input from user (Password) (salt)
    public static String encrypt(String data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        try{
            byte[] encryptedBytes = cipher.doFinal(data.getBytes());
            //returns encrypted code
            return Base64.encodeToString(encryptedBytes, Base64.DEFAULT);
            //Exception
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decrypt(String encryptedData, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        try{
            byte[] decryptedBytes = cipher.doFinal(Base64.decode(encryptedData, Base64.DEFAULT));
            //returns decrypted text
            return new String(decryptedBytes);
            //Exception
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}