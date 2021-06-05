package com.bms.tenants.util.ua;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserAgent {

	@JsonAlias("ua_string")
	private String uaString;

	@JsonAlias("ua_class")
	private String uaClass;

	@JsonAlias("ua_version")
	private String ua;

	@JsonAlias("ua_family")
	private String uaFamily;

	@JsonAlias("os")
	private String uaOs;

	@JsonAlias("os_family")
	private String uaOsFamily;

	@JsonAlias("device_class")
	private String uaDevice;

	@JsonAlias("device_vendor")
	private String uaDeviceVendor;

}
