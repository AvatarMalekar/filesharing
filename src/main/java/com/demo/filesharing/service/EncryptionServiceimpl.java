package com.demo.filesharing.service;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

@Service
public class EncryptionServiceimpl implements EncryptionService{
    private static final String ALGORITHM = "AES";
    
    
    private byte[] generateKey(byte[] key) {
//        byte[] key = passcode.getBytes(StandardCharsets.UTF_8);
        MessageDigest sha = null;
		try {
			sha = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16); // Use first 16 bytes (128-bit key)
        return new SecretKeySpec(key, ALGORITHM).getEncoded();
//        return key;
    }
   
    public byte[] encrypt(byte[] data, String key){
        // Create AES key from the provided key string (16 bytes for AES-128)
        SecretKeySpec secretKey = new SecretKeySpec(generateKey(key.getBytes()), ALGORITHM);

        // Initialize Cipher for encryption
        Cipher cipher = null;
        byte[] doFinal =null;
		try {
			cipher = Cipher.getInstance(ALGORITHM);
			 cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			 doFinal = cipher.doFinal(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
        // Encrypt the data
        // Returns encrypted data as byte[]
		return doFinal;
    }
    
    public byte[] decrypt(byte[] encryptedData, String key){
        // Create AES key from the provided key string (16 bytes for AES-128)
        SecretKeySpec secretKey = new SecretKeySpec(generateKey(key.getBytes()), ALGORITHM);
        byte[] doFinal= null;
        try {
        	Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            doFinal = cipher.doFinal(encryptedData);
        }
        catch(Exception e){
        	e.printStackTrace();
        }
        return  doFinal; 
    }

}
