package com.bms.tenants.tenant;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository extends MongoRepository<Tenant, String> {

	Optional<Tenant> findById(String id);

	boolean existsByName(String name);
}
