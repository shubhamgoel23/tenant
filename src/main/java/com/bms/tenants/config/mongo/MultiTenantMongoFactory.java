package com.bms.tenants.config.mongo;

import java.util.Optional;

import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

import com.bms.tenants.config.context.TenantContext;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MultiTenantMongoFactory extends SimpleMongoClientDatabaseFactory {

	private final String databaseName;

	public MultiTenantMongoFactory(MongoClient mongoClient, String databaseName) {
		super(mongoClient, databaseName);
		this.databaseName = databaseName;
	}

	@Override
	public MongoDatabase getMongoDatabase() {

		return getMongoClient().getDatabase(getTenantDatabase());
	}

	protected String getTenantDatabase() {
		return Optional.ofNullable(TenantContext.getTenant()).orElse(databaseName);
	}
}
