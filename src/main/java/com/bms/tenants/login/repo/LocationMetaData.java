package com.bms.tenants.login.repo;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "locationMetaData")
public class LocationMetaData implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	private float lat;
	private float lon;
	private String country;
	private String region;
	private String ipAddress;
	

}
