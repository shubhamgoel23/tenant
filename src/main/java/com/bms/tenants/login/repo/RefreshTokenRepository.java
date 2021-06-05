package com.bms.tenants.login.repo;

import java.util.Optional;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface RefreshTokenRepository extends MongoRepository<RefreshToken, String> {
	
	Optional<RefreshToken> findByIdAndIsActiveTrue(String id);

}
