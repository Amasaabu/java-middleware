package com.sample.middleware.services;


import com.sample.middleware.errors.BadRequest;
import com.sample.middleware.models.MerchantConfiguration;
import com.sample.middleware.models.Request.MerchantConfigurationRequest;
import com.sample.middleware.repository.CountryRepo;
import com.sample.middleware.repository.MerchantConfigRepo;
import com.sample.middleware.repository.ProviderRepo;
import com.sample.middleware.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class MerchantConfigurationService {
    Utils utils;
    CountryRepo countryRepo;
    ProviderRepo providerRepo;
    MerchantConfigRepo merchantConfigRepo;
    public MerchantConfiguration createConfiguration(String merchantId, MerchantConfigurationRequest merchantConfigurationRequest){
        var config = new MerchantConfiguration();
        var configId= utils.generateId("CFG");
        config.setConfigurationId(configId);
        config.setTag(merchantConfigurationRequest.getTag());
        config.setCurrencyConstrint(merchantConfigurationRequest.getCurrencyConstrint());
        config.setMaxAmmount(merchantConfigurationRequest.getMaxAmount());
        config.setMerchantId(merchantId);
        config.setMinAmount(merchantConfigurationRequest.getMinAmount());

        config.setCurrencyConstrint(merchantConfigurationRequest.getCurrencyConstrint());
        try {
            //search for country
            var country = countryRepo.findById((long) merchantConfigurationRequest.getOriginCountry());
            country.ifPresent(config::setOriginCountry);
        } catch (Exception e) {
            throw new BadRequest("Something went wrong trying to modify resource");
        }
        try {
            //search for provider
            var provider = providerRepo.findByName(merchantConfigurationRequest.getProviderName());
            provider.ifPresent(config::setProvider);
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        merchantConfigRepo.save(config);
        return  config;
    }
    public List<MerchantConfiguration> getConfigurations(String concreteId) {
        return merchantConfigRepo.findByMerchantId(concreteId);
    }

}
