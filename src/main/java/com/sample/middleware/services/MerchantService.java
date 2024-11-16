package com.sample.middleware.services;

import com.sample.middleware.models.MerchantDetails;
import com.sample.middleware.models.Request.MerchantRequest;
import com.sample.middleware.repository.MerchantDetailsRepositoryTable;
import com.sample.middleware.utils.Encryptor;
import com.sample.middleware.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MerchantService {
    MerchantDetailsRepositoryTable merchantDetailsRepositoryTable;
    Utils utils;
    Encryptor encryptor;

    public MerchantDetails saveNewMerchant(MerchantRequest merchantRequest){
        MerchantDetails merchantdet =  new MerchantDetails();
        merchantdet.setMerchantId(merchantRequest.getMerchantId());
        merchantdet.setLastname(merchantRequest.getFirstName());
        merchantdet.setFirstName(merchantRequest.getFirstName());
        merchantdet.setCountry(merchantRequest.getCountry());
        merchantdet.setPhone(merchantRequest.getPhone());
        merchantdet.setEmail(merchantRequest.getEmail());
        merchantdet.setMerchantId(utils.generateId("MCT", 10));
        merchantdet.setPassword(merchantRequest.getPassword());
        merchantdet.setVerified(false);
        //encrypt password
        var encyptedPassword = encryptor.encrypt(merchantRequest.getPassword());
        merchantdet.setPassword(encyptedPassword);
        return merchantDetailsRepositoryTable.save(merchantdet);
    }

    public Object getMercahntDetails(String merchantId) {
        var merchant = merchantDetailsRepositoryTable.findByMerchantId(merchantId);
        if (merchant.isEmpty()) {
            //throw invalid exceprion
        }
        //remove password from every response sent back
        merchant.get().setPassword("");
        return merchant.get();
    }
}
