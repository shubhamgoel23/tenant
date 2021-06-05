package com.bms.tenants.login.repo;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "deviceMetaData")
public class DeviceMetaData implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String uaString;

	private String uaClass;

	private String ua;

	private String uaFamily;

	private String uaOs;

	private String uaOsFamily;

	private String uaDevice;

	private String uaDeviceVendor;

}
