package com.bms.tenants.login;

import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.stereotype.Service;

@Service
public class TokenEncryptionService {

	private final AES256TextEncryptor encryptor;

	public TokenEncryptionService() {
		encryptor = new AES256TextEncryptor();
		encryptor.setPassword("randomPassword");

	}

	public String encrypt(String token) {
		return encryptor.encrypt(token);
	}

	public String decrypt(String token) {
		return encryptor.decrypt(token);
	}
}
