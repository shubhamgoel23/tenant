package com.bms.tenants.login.repo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.bms.tenants.user.AppUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "loginMetaData")
public class LoginMetaData implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@DBRef
	private AppUser appUser;

	@DBRef
	private DeviceMetaData deviceMetaData;

	@DBRef
	private LocationMetaData locationMetaData;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date lastLoggedIn;

}
