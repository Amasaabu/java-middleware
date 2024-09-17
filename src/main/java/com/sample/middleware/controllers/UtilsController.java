package com.sample.middleware.controllers;


import com.sample.middleware.errors.BadRequest;
import com.sample.middleware.models.Country;
import com.sample.middleware.models.Provider;
import com.sample.middleware.models.Responses.CustomResponse;
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
@RequestMapping("/api/utils")
@AllArgsConstructor
public class UtilsController {
    UtilServices utilServices;
    MerchantService merchantService;
    @GetMapping("/countries")
    public ResponseEntity<CustomResponse> getCountries(HttpServletRequest req) {

        var countries = utilServices.getCountries();
        CustomResponse resp = new CustomResponse();
        resp.setMessage(countries);
        resp.setCode(Responses.SUCCESSFUL.getCode());
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }
    @GetMapping("/providers")
    public ResponseEntity<CustomResponse> getProvider(HttpServletRequest req) {

        var providers = utilServices.getProviders();
        CustomResponse resp = new CustomResponse();
        resp.setMessage(providers);
        resp.setCode(Responses.SUCCESSFUL.getCode());
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }
    @PostMapping("/providers")
    public ResponseEntity<CustomResponse> addProvider(HttpServletRequest req, @Valid @RequestBody Provider request) {
        var concreteId = SecurityContextHolder.getContext().getAuthentication().getName();
        //only add provider if current user is an admin
        var merchant = merchantService.getMercahntDetails(concreteId);
        if (!merchant.getIsAdmin()) {
            throw new BadRequest("User not allowed to take this action");
        }
        var savedProvider =  utilServices.addProvider(request);

        CustomResponse resp = new CustomResponse();
        resp.setMessage(savedProvider);
        resp.setCode(Responses.SUCCESSFUL.getCode());
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }
    @PostMapping("/country")
    public ResponseEntity<CustomResponse> addCountry(HttpServletRequest req, @Valid @RequestBody Country request) {
        var concreteId = SecurityContextHolder.getContext().getAuthentication().getName();
        //only add provider if current user is an admin
        var merchant = merchantService.getMercahntDetails(concreteId);
        if (!merchant.getIsAdmin()) {
            throw new BadRequest("User not allowed to take this action");
        }
        var savedProvider =  utilServices.addCountry(request);

        CustomResponse resp = new CustomResponse();
        resp.setMessage(savedProvider);
        resp.setCode(Responses.SUCCESSFUL.getCode());
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }
}
