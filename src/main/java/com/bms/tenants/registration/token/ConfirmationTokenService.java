package com.bms.tenants.registration.token;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

	private final ConfirmationTokenRepository confirmationTokenRepository;

	public void saveConfirmationToken(ConfirmationToken token) {
		confirmationTokenRepository.save(token);
	}

	public Optional<ConfirmationToken> getToken(String token) {
		return confirmationTokenRepository.findByToken(token);
	}

	public void setConfirmedAt(String token) {
		ConfirmationToken cToken = confirmationTokenRepository.findByToken(token).orElseThrow(()->new RuntimeException());
		cToken.setConfirmedAt(LocalDateTime.now());
		confirmationTokenRepository.save(cToken);
	}
}