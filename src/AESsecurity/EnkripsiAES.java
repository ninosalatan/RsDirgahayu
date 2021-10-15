/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AESsecurity;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;


/**
 *
 * @author khanzamedia
 */
public class EnkripsiAES {
    private static String key = "Bar12345Bar12345"; // 128 bit key
    private static String initVector = "sayangsamakhanza"; // 16 bytes IV
        
    public static String decrypt(String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

            return new String(original);
        } catch (Exception ex) {
            System.out.println("Ciluk Baaaaaaaa!!!!!");
        }

        return null;
    }
    public static String decrypts(String key,String cipherText) {   	      	   
        try {
        	 MessageDigest digest = MessageDigest.getInstance("SHA-256");
 		    byte[] _hashKey = digest.digest(key.getBytes(StandardCharsets.UTF_8));
 		    byte[] _hashIv = new byte[16];
 		    for (int i = 0; i < 16; i++) {
 			    _hashIv[i] = _hashKey[i];
 	        }
 		   SecretKeySpec _key = new SecretKeySpec(_hashKey, "AES");
 		   IvParameterSpec iv = new IvParameterSpec(_hashIv);
 		   
 		    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        cipher.init(Cipher.DECRYPT_MODE, _key, iv);
	        byte[] plainText = cipher.doFinal(Base64.decodeBase64(cipherText));
	       // String respon = LZString.decompressFromEncodedURIComponent(plainText);
//	        System.out.println("ChiperText= "+cipherText);
//	        System.out.println("iv= "+iv);
//	        System.out.println("key= "+_key);
	        return new String(plainText);
        	
        } catch (Exception ex) {
            System.out.println("Ciluk Baaaaaaaa!!!!!");
        }

        return null;
    }
    
    public static String encrypt(String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            System.out.println("None!!!!!");
        }

        return null;
    }
    
}
