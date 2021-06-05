package com.bms.tenants.login.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class TokenDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String token;
	private final String refreshToken;

}
