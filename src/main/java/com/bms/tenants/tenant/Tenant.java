package com.bms.tenants.tenant;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "tenant")
public class Tenant implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String name;
}
