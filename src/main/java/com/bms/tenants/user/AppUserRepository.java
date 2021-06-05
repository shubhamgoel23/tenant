package com.bms.tenants.user;

import java.util.Optional;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
//@Transactional(readOnly = true)
@JaversSpringDataAuditable
public interface AppUserRepository extends MongoRepository<AppUser, Long> {

	Optional<AppUser> findByEmail(String email);

	@Query("UPDATE AppUser a " + "SET a.enabled = TRUE WHERE a.email = ?1")
	int enableAppUser(String email);
}
