package com.bms.tenants.login;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bms.tenants.login.repo.LoginMetaData;
import com.bms.tenants.login.repo.RefreshToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtUtilsService {

//	private final RefreshTokenService refreshTokenService;
	private final TokenEncryptionService tokenEncryptionService;
	private static String secret = "secursecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuree";

	public String generateRefreshToken(LoginMetaData loginMetaData, RefreshToken refreshToken) {
		return genrateToken(loginMetaData.getAppUser().getAuthorities(),
				loginMetaData.getLocationMetaData().getIpAddress(), loginMetaData.getDeviceMetaData().getUaString(),
				refreshToken.getId(), loginMetaData.getAppUser().getId(), refreshToken.getCreatedAt(),
				refreshToken.getExpiresAt());

	}

	public String genrateToken(Collection<? extends GrantedAuthority> authorities, String ipAddress, String ua,
			String id, String userId, Date createdAt, Date expiresAt) {
		final Map<String, Object> claims = new HashMap<>();
		claims.put("authorities", authorities);
		claims.put("ip", ipAddress);
		claims.put("ua", ua);
		String token = Jwts.builder().setClaims(claims).setId(id).setSubject(userId).setIssuedAt(createdAt)
				.setExpiration(expiresAt).signWith(Keys.hmacShaKeyFor(secret.getBytes())).compact();
		return tokenEncryptionService.encrypt(token);
	}

	// retrieve username from jwt token
	public String getSubjectFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	// retrieve token id from jwt token
	public String getTokenIdFromToken(String token) {
		return getClaimFromToken(token, Claims::getId);
	}

	// retrieve expiration date from jwt token
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	// retrieve not before date from jwt token
	public Date getNotBeforeDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getNotBefore);
	}

	// get authoritites
	@SuppressWarnings("unchecked")
	public Collection<? extends GrantedAuthority> getAuthorities(String token) {
		return getClaimFromToken(token, claims -> (Collection<? extends GrantedAuthority>) claims.get("authorities"));
	}

	// get ip address
	public String getIpAddress(String token) {
		return getClaimFromToken(token, claims -> (String) claims.get("ip"));
	}

	// get user agent
	public String getUserAgent(String token) {
		return getClaimFromToken(token, claims -> (String) claims.get("ua"));
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	// for retrieving any information from token we will need the secret key
	private Claims getAllClaimsFromToken(String token) {
		Jws<Claims> jws;

		try {
			jws = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build()
					.parseClaimsJws(token);

			return jws.getBody();
		} catch (JwtException ex) {
			throw new RuntimeException();
		}

	}

	private Boolean isValidIpLocation(String token) {
		Boolean flag = false;
		String ipAddress = getIpAddress.get();
		if (Objects.equals(ipAddress, getIpAddress(token)))
			flag = true;

		return flag;

	}

	private Boolean isValidUserAgent(String token) {
		Boolean flag = false;
		String userAgent = getUserAgent.get();
		if (Objects.equals(userAgent, getUserAgent(token)))
			flag = true;

		return flag;

	}

	public static final Supplier<String> getUserAgent = () -> {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		var ua = "";
		if (!ObjectUtils.isEmpty(request))
			ua = request.getHeader("User-Agent");

		return ua;
	};

	public static final Supplier<String> getIpAddress = () -> {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		String clientIp;
		String clientXForwardedForIp = request.getHeader("x-forwarded-for");
		if (Objects.nonNull(clientXForwardedForIp)) {
			clientIp = parseXForwardedHeader(clientXForwardedForIp);
		} else {
			clientIp = request.getRemoteAddr();
		}
		return clientIp;
	};

	private static String parseXForwardedHeader(String header) {
		return header.split(" *, *")[0];
	}

	// check if the token has expired
	private Boolean isTokenExpired(String token) {
		final var expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	// validate token
	public Boolean validateToken(String token) {
		final String username = getSubjectFromToken(token);
		return username != null && !isTokenExpired(token) && isValidIpLocation(token) && isValidUserAgent(token);
	}

	public String generateTokenFromRefreshToken(String refreshToken) {
		// decryt refresh token
		String token = tokenEncryptionService.decrypt(refreshToken);
		// validate refresh token from id
		boolean valid = validateToken(token);
		// verify refresh token
//		boolean valid01 = refreshTokenService.isValidRefreshToken(getTokenIdFromToken(token));
		// extract user details from refresh token

		// create access token
		return genrateToken(getAuthorities(token), getIpAddress.get(), getUserAgent.get(), UUID.randomUUID().toString(),
				getSubjectFromToken(token), new Date(), java.sql.Date.valueOf(LocalDate.now().plusDays(1)));

	}
}
