package com.sample.middleware.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import com.sample.middleware.models.Request.MerchantRequest;
import com.sample.middleware.models.Responses.CustomResponse;
import com.sample.middleware.services.APIKeyService;
import com.sample.middleware.services.MerchantService;
import com.sample.middleware.utils.Responses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
@RestController
@RequestMapping("/api/merchant")
@AllArgsConstructor
public class Controllers {
    APIKeyService apiKeyService;
    MerchantService merchantService;
    @PostMapping(path = "/signup")
    public ResponseEntity<CustomResponse> createUser (HttpServletRequest req, @Valid @RequestBody MerchantRequest request) throws JsonProcessingException {
        var merchant = merchantService.saveNewMerchant(request);
        var resp = new CustomResponse();
         resp.setCode("200");
         resp.setMessage("Successful");
//        resp.setMessage(merchant);
//        String merchantJson = new Gson().toJson(merchant);
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }
    @PostMapping(path = "/generatekey")
    public ResponseEntity<CustomResponse> createAPIKey(HttpServletRequest req) throws JsonProcessingException {
        System.out.println("GENERATIG NEW API KEY");
        var concreteId = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("COncreteId" + concreteId);
        var merchant = merchantService.getMercahntDetails(concreteId);
        var APIKey = apiKeyService.createAPIKEY(merchant);
        var resp = new CustomResponse();
        resp.setMessage(APIKey);
        resp.setCode("200");
//        resp.setMessage(merchant);
//        String merchantJson = new Gson().toJson(merchant);
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }

    @GetMapping(path = "/merchant")
    public  ResponseEntity<CustomResponse> retrivemerchant  (HttpServletRequest req) {
        var concreteId = SecurityContextHolder.getContext().getAuthentication().getName();
       var cre = SecurityContextHolder.getContext().getAuthentication().getCredentials();
       System.out.println(String.valueOf(cre));
        var merchant = merchantService.getMercahntDetails(concreteId);
        var response = new CustomResponse();

        response.setMessage(merchant);
        response.setCode(String.valueOf(Responses.SUCCESSFUL));
        response.setResponseTime(Responses.SUCCESSFUL.getCode());
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @GetMapping("/test")
    public ResponseEntity<CustomResponse> test(HttpServletRequest req) {
        CustomResponse resp = new CustomResponse();
        resp.setMessage("working");
        resp.setCode("200");
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }
}
