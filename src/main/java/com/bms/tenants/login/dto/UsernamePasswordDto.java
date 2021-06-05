package com.bms.tenants.login.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
@AllArgsConstructor
public class UsernamePasswordDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank
	private String username;

	@NotBlank
	private String password;

}
