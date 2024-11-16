package com.sample.middleware.services;


import com.sample.middleware.models.APIKey;
import com.sample.middleware.models.MerchantDetails;
import com.sample.middleware.repository.APIKeyRepository;
import com.sample.middleware.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class APIKeyService {

    Utils utils;
    APIKeyRepository apiKeyRepository;
    //create APIKEY
    public APIKey createAPIKEY (MerchantDetails merchant){
        APIKey apiKey = new APIKey();
        apiKey.setValue(utils.generateId("API-", 12));
        apiKey.setMerchant(merchant);

        return apiKeyRepository.save(apiKey);
    }


}
