package com.sample.middleware.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
@Table(indexes =
        {@Index(name = "phone", columnList = "phone")})
public class MerchantDetails {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int id;
    private String firstName;
    private String lastname;
    private String password;
    @Column(unique = true, length = 255)
    private  String email;
    @Column(unique = true, length = 21)
    private String merchantId;
    private String phone;
    private String country;
    private Boolean verified;
    private Boolean isAdmin=false;


}
