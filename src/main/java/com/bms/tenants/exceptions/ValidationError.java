package com.bms.tenants.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ValidationError extends SubError {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String object;
	private String field;
	private Object rejectedValue;
	private String message;

}
