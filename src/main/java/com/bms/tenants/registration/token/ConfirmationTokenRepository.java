package com.bms.tenants.registration.token;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationTokenRepository extends MongoRepository<ConfirmationToken, String> {

	Optional<ConfirmationToken> findByToken(String token);

	@Query("UPDATE ConfirmationToken c " + "SET c.confirmedAt = ?2 " + "WHERE c.token = ?1")
	int updateConfirmedAt(String token, LocalDateTime confirmedAt);
}