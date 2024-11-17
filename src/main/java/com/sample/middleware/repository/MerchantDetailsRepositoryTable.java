package com.sample.middleware.repository;

import com.sample.middleware.models.MerchantDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MerchantDetailsRepositoryTable extends JpaRepository<MerchantDetails, Long> {
    public Optional<MerchantDetails> findByMerchantId(String username);
    public Optional<MerchantDetails> findByEmail(String email);
}
