package org.tigersndragons.salonbooks.service;

import org.springframework.stereotype.Service;

@Service
public interface EncryptionService {
	public String encryptString(String s);
	
	public String decryptString(String s);
}
