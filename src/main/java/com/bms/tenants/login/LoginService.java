package com.bms.tenants.login;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.bms.tenants.login.dto.TokenDto;
import com.bms.tenants.login.dto.UsernamePasswordDto;
import com.bms.tenants.login.repo.DeviceMetaData;
import com.bms.tenants.login.repo.LocationMetaData;
import com.bms.tenants.login.repo.LoginMetaData;
import com.bms.tenants.login.repo.LoginMetaDataRepository;
import com.bms.tenants.user.AppUser;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {

	private final AuthenticationManager authenticationManager;

	private final LocationMetaDataService locationMetaDataService;

	private final DeviceMetaDataService deviceMetaDataService;

	private final LoginMetaDataRepository loginMetaDataRepository;

	private final RefreshTokenService refreshTokenService;
	
	private final JwtUtilsService jwtUtilsService;

	public TokenDto login(UsernamePasswordDto usernamePasswordDto) {
		try {
			// validate credentials
			UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
					usernamePasswordDto.getUsername(), usernamePasswordDto.getPassword());
			Authentication authenticate = authenticationManager.authenticate(authRequest);
			SecurityContextHolder.getContext().setAuthentication(authenticate);

			Optional<AppUser> user = getUserFromAuth.apply(authenticate);
			if (user.isPresent())
				System.out.println(user.get());

			auditLoginActivity(user.get());

		} catch (DisabledException e) {
			// get dummy token without authorities
			System.out.println(e);
		} catch (LockedException e) {
			// get dummy token without authorities
			System.out.println(e);
		} catch (AccountExpiredException e) {
			// get dummy token without authorities
			System.out.println(e);
		} catch (BadCredentialsException e) {
			// get dummy token without authorities
			System.out.println(e);
		} catch (Exception e) {
			// no token
			System.out.println(e);
		}

		return null;
	}

	private void auditLoginActivity(AppUser appUser) {
		DeviceMetaData deviceMetaData = deviceMetaDataService.saveLoginDeviceMetaData();
		// get and store device info

		// get and store location info
		LocationMetaData locationMetaData = locationMetaDataService.saveLoginLocMetaData();

		LoginMetaData loginMetaData = LoginMetaData.builder().appUser(appUser).deviceMetaData(deviceMetaData)
				.locationMetaData(locationMetaData).lastLoggedIn(new Date()).build();
		loginMetaData = loginMetaDataRepository.save(loginMetaData);

		// get refresh token
		String refreshToken = refreshTokenService.saveRefreshToken(loginMetaData);
		
		String accessToken = jwtUtilsService.generateTokenFromRefreshToken(refreshToken);

	}

	public static final Function<Authentication, Optional<AppUser>> getUserFromAuth = auth -> {

		Optional<AppUser> apOptional = Optional.empty();

		if (Objects.isNull(auth))
			return apOptional;

		Object principal = auth.getPrincipal();

		if (principal instanceof UserDetails)
			apOptional = Optional.of((AppUser) principal);

		return apOptional;

	};

}
