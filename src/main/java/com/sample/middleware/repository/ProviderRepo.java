package com.sample.middleware.repository;

import com.sample.middleware.models.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProviderRepo extends JpaRepository<Provider, Long> {
    public Optional<Provider> findByName(String providername);
}
