package com.bms.tenants.config.mongo;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

@EnableMongoAuditing
public class MongoAuditing implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
       String userName= SecurityContextHolder.getContext().getAuthentication().getName();
        return Optional.of(userName);
    }
}