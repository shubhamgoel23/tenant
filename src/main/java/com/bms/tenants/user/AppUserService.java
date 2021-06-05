package com.bms.tenants.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.checkerframework.checker.units.qual.s;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bms.tenants.registration.token.ConfirmationToken;
import com.bms.tenants.registration.token.ConfirmationTokenService;
import com.bms.tenants.tenant.Tenant;
import com.bms.tenants.tenant.TenantRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppUserService implements UserDetailsService {

	private static final String USER_NOT_FOUND_MSG = "user with email %s not found";

	private final AppUserRepository appUserRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final ConfirmationTokenService confirmationTokenService;
	private final TenantRepository tenantRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDetails user = appUserRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username)));
		return user;
	}

	@Transactional
	public String signUpUser(AppUser appUser) {
//		appUser.
		boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();

		if (userExists) {
			// TODO check of attributes are the same and
			// TODO if email not confirmed send confirmation email.

			throw new IllegalStateException("email already taken");
		}

		String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());

		appUser.setPassword(encodedPassword);
		
		Tenant tenant2 = tenantRepository.save(Tenant.builder().name(UUID.randomUUID().toString()).build());
		appUser.setTenant(tenant2);

		appUserRepository.save(appUser);
		String token = UUID.randomUUID().toString();
		ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
				LocalDateTime.now().plusMinutes(15), appUser);

		confirmationTokenService.saveConfirmationToken(confirmationToken);

		return token;
	}

	public void enableAppUser(String email) {
		AppUser userDetails = (AppUser) loadUserByUsername(email);
		userDetails.setEnabled(true);
		appUserRepository.save(userDetails);
	}

}
