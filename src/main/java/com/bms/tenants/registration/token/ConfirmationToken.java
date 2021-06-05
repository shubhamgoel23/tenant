package com.bms.tenants.registration.token;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bms.tenants.user.AppUser;

import lombok.Data;

@Data
@Document(collection = "confirmationToken")
public class ConfirmationToken implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String token;
	private LocalDateTime createdAt;
	private LocalDateTime expiresAt;
	private LocalDateTime confirmedAt;
	@DBRef
	private AppUser appUser;

	public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt, AppUser appUser) {
		super();
		this.token = token;
		this.createdAt = createdAt;
		this.expiresAt = expiresAt;
		this.appUser = appUser;
	}

}
