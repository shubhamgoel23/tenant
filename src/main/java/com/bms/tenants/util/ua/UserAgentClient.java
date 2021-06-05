package com.bms.tenants.util.ua;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "userAgent", url = "https://api.udger.com/")
public interface UserAgentClient {

	@GetMapping(path = "/v3/parse")
	ResponseEntity<UdgerResponse> getUserAgent(@RequestParam("accesskey") String accessKey,
			@RequestParam("ua") String ua);

}
