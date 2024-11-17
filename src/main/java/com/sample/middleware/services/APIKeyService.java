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

import java.util.Arrays;
import java.util.List;

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

    public boolean validateAPIKey(String apiKey, String merchantIdFromContext) {
        //retrive key
        var APIKey = apiKeyRepository.findByValue(apiKey);
        if (APIKey.isEmpty()) {
            System.out.println("Error, no API Key Found");
            return false;
        }
        //validate if API key is active
        //ensure merchant id is equal to api key provided
        if (!(APIKey.get().getMerchant().getMerchantId().equals(merchantIdFromContext))) {
            return false;
        }
//        if (APIKey.get().getMerchant().getMerchantId().equals( merchant.getMerchantId())) {
//            System.out.println("Invalid API key");
//            return false;
//        }
        return true;
    }

    public boolean deactivateKey (String value){
        try {
            var key = apiKeyRepository.findByValue(value);
            if (key.isEmpty()) {
                throw new BadRequest("FATAL: Unnable to deactivate key");
            }
            key.get().setActive(false);
            apiKeyRepository.save(key.get());
            return true;
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return false;
        }


    }
    public List<APIKey> getAllAPIKey (String concreteId) {

            var merchant = merchantDetailsRepositoryTable.findByMerchantId(concreteId);
            if (merchant.isEmpty()) throw new BadRequest("Merchant not found");
            return apiKeyRepository.findByMerchant(merchant.get());


    }
    public boolean deleteKey (String value){
        try {
            var key = apiKeyRepository.findByValue(value);
            if (key.isEmpty()) {
                throw new BadRequest("FATAL: Unnable to delete key");
            }
            apiKeyRepository.delete(key.get());
            return true;
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return false;
        }


    }


}
