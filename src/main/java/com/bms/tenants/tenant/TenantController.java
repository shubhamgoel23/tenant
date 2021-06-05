package com.bms.tenants.tenant;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TenantController {

	private final TenantService tenantService;

	@PostMapping(value = "/tenants", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TenantDto> createTenant(@RequestParam String tenant) {
		return new ResponseEntity<>(tenantService.createTenant(tenant), HttpStatus.OK);
	}

}
