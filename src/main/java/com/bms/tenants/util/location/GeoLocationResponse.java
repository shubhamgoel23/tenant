package com.bms.tenants.util.location;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GeoLocationResponse {

	private String status;
	private String continent;
	private String continentCode;
	private String country;
	private String countryCode;
	private String region;
	private String regionName;
	private String city;
	private String district;
	private String zip;
	private float lat;
	private float lon;
	private String timeZone;
	private int offset;
	private String currency;
	private String isp;
	private String org;
	private String as;
	private String asname;
	private String reverse;
	private boolean mobile;
	private boolean proxy;
	private boolean hosting;

}
