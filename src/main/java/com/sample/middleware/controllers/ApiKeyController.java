package com.sample.middleware.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sample.middleware.errors.BadRequest;
import com.sample.middleware.models.Country;
import com.sample.middleware.models.Provider;
import com.sample.middleware.models.Request.APIKeyRequest;
import com.sample.middleware.models.Responses.CustomResponse;
import com.sample.middleware.services.APIKeyService;
import com.sample.middleware.services.MerchantService;
import com.sample.middleware.services.UtilsServices.UtilServices;
import com.sample.middleware.utils.Responses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/key")
@AllArgsConstructor
public class ApiKeyController {
    UtilServices utilServices;
    APIKeyService apiKeyService;

    @PostMapping(path = "/generate")
    public ResponseEntity<CustomResponse> createAPIKey(HttpServletRequest req, @Valid @RequestBody APIKeyRequest request) throws JsonProcessingException {
        System.out.println("GENERATIG NEW API KEY");
        var concreteId = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("COncreteId" + concreteId);
        var APIKey = apiKeyService.createAPIKEY(concreteId, request);
        APIKey.setMerchant(null);
        var resp = new CustomResponse();
        resp.setMessage(APIKey);
        resp.setCode("200");
//        resp.setMessage(merchant);
//        String merchantJson = new Gson().toJson(merchant);
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }
    //DEACTIVATE KEY
    @PostMapping(path = "/deactivate")
    public ResponseEntity<CustomResponse> deactivateKet(HttpServletRequest req, @Valid @RequestBody APIKeyRequest request) {
        var concreteId = SecurityContextHolder.getContext().getAuthentication().getName();
        var apikeyValidation = apiKeyService.validateAPIKey(request.getValue(), concreteId);
        if (!apikeyValidation) {
            throw  new BadRequest("API key could not be validated to match the user");
        }
        var result = apiKeyService.deactivateKey(request.getValue());
        var resp = new CustomResponse();
        if (!result) {
            resp.setCode("400");
            resp.setMessage("Operation Failed");
        }
        resp.setCode("200");
        resp.setMessage("Operation Completed");


        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }
    //Get keys by a merchant
    @GetMapping(path = "/get")
    public ResponseEntity<CustomResponse> getKey(HttpServletRequest req, @Valid @RequestBody APIKeyRequest request) {
        var concreteId = SecurityContextHolder.getContext().getAuthentication().getName();
        var apikeys = apiKeyService.getAllAPIKey(concreteId);

        var resp = new CustomResponse();
        resp.setMessage(apikeys);
        resp.setCode("200");
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }
    //DELETE KEY
    @DeleteMapping(path = "/delete")
    public ResponseEntity<CustomResponse> deleteKey(HttpServletRequest req, @Valid @RequestBody APIKeyRequest request) {
        var concreteId = SecurityContextHolder.getContext().getAuthentication().getName();
        var apikeyValidation = apiKeyService.validateAPIKey(request.getValue(), concreteId);
        if (!apikeyValidation) {
            throw new BadRequest("API key could not be validated to match the user");
        }
        var result = apiKeyService.deleteKey(request.getValue());
        var resp = new CustomResponse();
        if (!result) {
            resp.setCode("400");
            resp.setMessage("Operation Failed");
        }
        resp.setCode("200");
        resp.setMessage("Operation Completed");


        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }
}
