package com.bms.tenants.registration;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
@AllArgsConstructor
public class RegistrationRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank
	private final String firstName;
	@NotBlank
	private final String lastName;
	@NotBlank
	@Size(max = 320)
	private final String email;
	@NotBlank
	private final String password;
}
