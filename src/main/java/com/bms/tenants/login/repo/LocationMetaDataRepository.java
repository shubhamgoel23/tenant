package com.bms.tenants.login.repo;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface LocationMetaDataRepository extends MongoRepository<LocationMetaData, String> {

}
