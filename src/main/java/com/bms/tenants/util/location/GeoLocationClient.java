package com.bms.tenants.util.location;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "geoLocation", url = "http://ip-api.com")
public interface GeoLocationClient {

	@GetMapping(path = "/json/{ip-address}")
	ResponseEntity<GeoLocationResponse> getGeoLocation(@PathVariable("ip-address") String ipAddress,
			@RequestParam("fields") String fields);

}
