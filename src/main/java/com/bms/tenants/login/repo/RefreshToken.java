package com.bms.tenants.login.repo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "refreshToken")
public class RefreshToken implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private Date createdAt;
	private Date expiresAt;
	private boolean isActive;

	@DBRef
	private LoginMetaData loginMetaData;

}
