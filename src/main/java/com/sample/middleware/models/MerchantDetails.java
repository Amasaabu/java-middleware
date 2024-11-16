package com.sample.middleware.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
@Table(indexes =
        {@Index(name = "merchantId", columnList = "merchantId"), @Index(name = "tag", columnList = "tag"), @Index(name = "originCountry", columnList = "originCountry"), @Index(name = "configurationId", columnList = "configurationId")})
public class MerchantDetails {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int id;
    private String firstName;
    private String lastname;
    private String password;
    @Column(unique = true)
    private  String email;
    private String merchantId;
    private String phone;
    private String country;
    private Boolean verified;


}
