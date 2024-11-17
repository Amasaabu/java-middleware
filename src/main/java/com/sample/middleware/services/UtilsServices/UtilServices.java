package com.sample.middleware.services.UtilsServices;

import com.sample.middleware.models.Country;
import com.sample.middleware.models.Provider;
import com.sample.middleware.repository.CountryRepo;
import com.sample.middleware.repository.ProviderRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UtilServices {
    CountryRepo countryRepo;
    ProviderRepo providerRepo;
    //retrieve configurable countries from db
    public List<Country> getCountries (){
        return countryRepo.findAll();
    }
    public List<Provider> getProviders (){
        return providerRepo.findAll();
    }
    public Provider addProvider(Provider providerToSave){
        return providerRepo.save(providerToSave);
    }
    public Country addCountry(Country countryToSave){
        return countryRepo.save(countryToSave);
    }
}