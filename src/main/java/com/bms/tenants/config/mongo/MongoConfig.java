package com.bms.tenants.config.mongo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.repository.mongo.MongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.bms.tenants.config.context.TenantContext;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.management.JMXConnectionPoolListener;

@Profile("!test")
@Configuration
@Import(MongoConfigProperties.class)
public class MongoConfig {

	@Primary
	@Bean
	public MongoDatabaseFactory mongoDbFactory(@Autowired MongoConfigProperties mongoConfigProperties) {
		return new MultiTenantMongoFactory(mongoClient(mongoConfigProperties), mongoConfigProperties.getDataBaseName());
	}

	@Bean
	public MongoTemplate mongoTemplate(@Autowired MongoDatabaseFactory mongoDbFactory) {
		return new MongoTemplate(mongoDbFactory);
	}

	@Bean
	public MongoClient mongoClient(MongoConfigProperties mongoConfigProperties) {
		List<ServerAddress> serverAddresses = mongoConfigProperties.getMongoConnection().entrySet().stream()
				.map(a -> new ServerAddress(a.getKey(), a.getValue())).collect(Collectors.toList());

		return MongoClients.create(MongoClientSettings.builder()
				.applyToConnectionPoolSettings(
						settings -> settings.addConnectionPoolListener(new JMXConnectionPoolListener()))
				.applyToClusterSettings(settings -> settings.hosts(serverAddresses)).build());
	}

	@Bean
	public Javers javers(@Autowired MongoConfigProperties mongoConfigProperties, @Autowired MongoClient mongoClient) {
		return JaversBuilder.javers()
				.registerJaversRepository(new MongoRepository(mongoClient.getDatabase(Optional
						.ofNullable(TenantContext.getTenant()).orElse(mongoConfigProperties.getDataBaseName()))))
				.build();
	}

}
