package com.sample.middleware.models.Request;

import com.sample.middleware.models.Country;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class MerchantConfigurationRequest {
    private String currencyConstrint;
    private String providerName;
    private BigDecimal maxAmount;
    private BigDecimal minAmount;
    private String merchantId;
    private int originCountry;
    private String tag;
}
