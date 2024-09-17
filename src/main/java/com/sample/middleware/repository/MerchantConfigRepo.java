package com.sample.middleware.repository;

import com.sample.middleware.models.MerchantConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MerchantConfigRepo extends JpaRepository<MerchantConfiguration, Long> {
   public List<MerchantConfiguration> findByMerchantId(String merchantId);
}
