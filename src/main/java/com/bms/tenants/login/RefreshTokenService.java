package com.bms.tenants.login;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bms.tenants.login.repo.LoginMetaData;
import com.bms.tenants.login.repo.RefreshToken;
import com.bms.tenants.login.repo.RefreshTokenRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

	private final RefreshTokenRepository refreshTokenRepository;
	private final JwtUtilsService jwtUtilsService;

	@Transactional
	public String saveRefreshToken(LoginMetaData loginMetaData) {
		var refreshToken = RefreshToken.builder().createdAt(new Date())
				.expiresAt(java.sql.Date.valueOf(LocalDate.now().plusDays(1))).loginMetaData(loginMetaData).build();
		refreshToken = refreshTokenRepository.save(refreshToken);
		return jwtUtilsService.generateRefreshToken(loginMetaData, refreshToken);
	}
	
	@Transactional
	public Boolean isValidRefreshToken(String tokenId) {
		Boolean flag = false;
		RefreshToken refreshToken = refreshTokenRepository.findByIdAndIsActiveTrue(tokenId).orElse(null);
		if(null == refreshToken)
			return flag;
		
		if(refreshToken.getExpiresAt().after(new Date()))
			flag = true;
		
		return flag;
		
	}

	public Optional<RefreshToken> findByToken(String token) {
//		return refreshTokenRepository.findByToken(token);
		return null;
	}

	public RefreshToken createRefreshToken(Long userId) {
//		RefreshToken refreshToken = new RefreshToken(UUID.randomUUID().toString(), Instant.now().toEpochMilli(),
//				Instant.now().plusMillis(refreshTokenDurationMs).toEpochMilli(), null);

//		RefreshToken refreshToken = refreshTokenRepository.save(refreshToken);
//		return refreshToken;
		return null;
	}

	public RefreshToken verifyExpiration(RefreshToken token) {
//		if (token.getExpiresAt() < Instant.now().toEpochMilli()) {
//			refreshTokenRepository.delete(token);
//			throw new Exception(token.getToken(),
//					"Refresh token was expired. Please make a new signin request");
//		}

		return token;
	}

	@Transactional
	public int deleteByUserId(Long userId) {
//		return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
		return 0;
	}

}
