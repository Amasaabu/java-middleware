package com.sample.middleware.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.sample.middleware.models.Request.MerchantConfigurationRequest;
import com.sample.middleware.models.Request.MerchantRequest;
import com.sample.middleware.models.Responses.CustomResponse;
import com.sample.middleware.services.MerchantConfigurationService;
import com.sample.middleware.utils.Responses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/configuration")
@AllArgsConstructor
public class ConfigurationController {
    MerchantConfigurationService merchantConfigurationService;
    @PostMapping(path = "/add")
    public ResponseEntity<CustomResponse> createUser (HttpServletRequest req, @Valid @RequestBody MerchantConfigurationRequest request) throws JsonProcessingException {
        var concreteId = SecurityContextHolder.getContext().getAuthentication().getName();
        var merchant = merchantConfigurationService.createConfiguration(concreteId, request);
        var resp = new CustomResponse();
        resp.setCode("200");
        resp.setMessage(merchant);
//        resp.setMessage(merchant);
//        String merchantJson = new Gson().toJson(merchant);
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }

    @GetMapping(path = "/configuration")
    public  ResponseEntity<CustomResponse> retrivconfigsByaMerchant (HttpServletRequest req) {
        var concreteId = SecurityContextHolder.getContext().getAuthentication().getName();

        var configs = merchantConfigurationService.getConfigurations(concreteId);
     //   var f = new Gson().toJson(configs);
        var response = new CustomResponse();
        response.setMessage(configs);
        response.setCode(String.valueOf(Responses.SUCCESSFUL));
        response.setResponseTime(Responses.SUCCESSFUL.getCode());
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

}
