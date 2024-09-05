package com.sample.middleware.models.Request;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class MerchantRequest {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int id;
    private String firstName;
    private String lastName;
    private String password;
    private  String email;
    private String merchantId;
    private String phone;
    private String country;
    private Boolean verified;


}

