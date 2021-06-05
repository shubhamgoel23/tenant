package com.bms.tenants.login;

import javax.annotation.security.PermitAll;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bms.tenants.login.dto.TokenDto;
import com.bms.tenants.login.dto.UsernamePasswordDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginController {

	private final LoginService loginService;

	@PermitAll
	@PostMapping(path = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> login(@RequestBody UsernamePasswordDto usernamePasswordDto, HttpServletResponse response) {
		TokenDto token = loginService.login(usernamePasswordDto);
//		Cookie cookie = new Cookie("Authorization", "Bearer " + token.getToken());
//		cookie.setHttpOnly(true);
//		response.addCookie(cookie);
		return new ResponseEntity<>("", HttpStatus.OK);
	}

}
