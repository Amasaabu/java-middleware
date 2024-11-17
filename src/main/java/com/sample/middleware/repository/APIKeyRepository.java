package com.sample.middleware.repository;

import com.sample.middleware.models.APIKey;
import com.sample.middleware.models.MerchantDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface APIKeyRepository extends JpaRepository<APIKey, Integer> {
    public Optional<APIKey> findByValue(String value);
    public List<APIKey> findByMerchant(MerchantDetails merchantDetails);
}
