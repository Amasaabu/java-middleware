package com.sample.middleware.services;


import com.sample.middleware.errors.BadRequest;
import com.sample.middleware.models.APIKey;
import com.sample.middleware.models.MerchantDetails;
import com.sample.middleware.models.Request.APIKeyRequest;
import com.sample.middleware.repository.APIKeyRepository;
import com.sample.middleware.repository.MerchantDetailsRepositoryTable;
import com.sample.middleware.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class APIKeyService {

    Utils utils;
    APIKeyRepository apiKeyRepository;
    MerchantDetailsRepositoryTable merchantDetailsRepositoryTable;
    //create APIKEY
    public APIKey createAPIKEY (String merchantId, APIKeyRequest request){
        APIKey apiKey = new APIKey();
        apiKey.setValue(utils.generateId("API-", 20));
        var merchant  = merchantDetailsRepositoryTable.findByMerchantId(merchantId);
        if (merchant.isEmpty()) {
            throw new BadRequest("Unnable to generate API Key");
        }
        apiKey.setMerchant(merchant.get());
        apiKey.setName(request.getName());
        apiKey.setActive(true);

        return apiKeyRepository.save(apiKey);
    }


}
