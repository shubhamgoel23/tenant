package com.bms.tenants.util.location;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GeoLocationService {

	private final GeoLocationClient geoLocationClient;

	public boolean isProxyLocation(String ipAddress) {
		GeoLocationResponse resp = getIPLocation(ipAddress);
		return resp.isProxy();
	}

	public boolean isMobileUser(String ipAddress) {
		GeoLocationResponse resp = getIPLocation(ipAddress);
		return resp.isMobile();
	}

	public GeoLocationResponse getIPLocation(String ipAddress) {
		ResponseEntity<GeoLocationResponse> resp = geoLocationClient.getGeoLocation(ipAddress, "66846719");
		return resp.getBody();
	}

}
