package com.bms.tenants.config.mongo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class MongoConfigProperties {

	@Value("${mongo.global}")
	private String dataBaseName;

	@Value("#{${mongo.connection.map}}")
	private Map<String, Integer> mongoConnection;

}