package com.bms.tenants.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class TenantBadRequestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TenantBadRequestException() {
		super();
	}

	public TenantBadRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public TenantBadRequestException(String message) {
		super(message);
	}
	
	public TenantBadRequestException(Throwable cause) {
		super(cause);
	}

}
