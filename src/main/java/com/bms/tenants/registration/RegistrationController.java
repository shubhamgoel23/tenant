package com.bms.tenants.registration;

import javax.annotation.security.PermitAll;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "api/v1/registration")
@RequiredArgsConstructor
public class RegistrationController {

	private final RegistrationService registrationService;

	@PostMapping
	@PermitAll
	public String register(@RequestBody RegistrationRequest request) {
		return registrationService.register(request);
	}

	@GetMapping(path = "confirm")
	@PermitAll
	public String confirm(@RequestParam("token") String token) {
		return registrationService.confirmToken(token);
	}

	@GetMapping
	public void validateResponse(@RequestParam("token") String token) {
//		captchaService.validateResponse(token);
	}
}