package com.bms.tenants.util.ua;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UdgerResponse {

	@JsonAlias("user_agent")
	private UserAgent userAgent;

}
