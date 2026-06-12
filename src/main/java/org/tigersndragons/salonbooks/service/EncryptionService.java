package org.tigersndragons.salonbooks.service;

public interface EncryptionService {
  String encryptString(String s);

  String decryptString(String s);
}
