package org.tigersndragons.salonbooks.service.impl;

import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.tigersndragons.salonbooks.service.EncryptionService;

@Service
public class EncryptionServiceImpl extends BaseServiceImpl implements EncryptionService {

  private static final byte[] RAW_KEY = {
    '1', '4', '5', '2', '3', '6', '7', '4', '8', '5', '8', 'a', 'Z', 'a', 'b', 'b'
  };
  private static final SecureRandom RND = new SecureRandom();
  private static final IvParameterSpec IV = new IvParameterSpec(RND.generateSeed(16));

  public String encryptString(String s) {
    try {
      return encrypt(s);
    } catch (Exception e) {
      return s;
    }
  }

  public String decryptString(String s) {
    try {
      return decrypt(s);
    } catch (Exception e) {
      return s;
    }
  }

  private static String encrypt(String value) throws GeneralSecurityException {
    SecretKeySpec skeySpec = new SecretKeySpec(RAW_KEY, "AES");
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    cipher.init(Cipher.ENCRYPT_MODE, skeySpec, IV);
    byte[] encrypted = cipher.doFinal(value.getBytes());
    return Base64.encodeBase64String(encrypted);
  }

  private static String decrypt(String encrypted) throws GeneralSecurityException {
    SecretKeySpec skeySpec = new SecretKeySpec(RAW_KEY, "AES");
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    cipher.init(Cipher.DECRYPT_MODE, skeySpec, IV);
    byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));
    return new String(original);
  }
}
