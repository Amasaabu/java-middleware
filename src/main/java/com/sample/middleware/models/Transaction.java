package com.sample.middleware.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Setter
@Getter
@Table(indexes =
        {@Index(name = "amount", columnList = "amount"), @Index(name = "cardPan", columnList = "cardPan"), @Index(name = "transactionId", columnList = "transactionId")})
public class Transaction {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String transactionId;
    private BigDecimal amount;
    private BigDecimal currency;
    private String routeId;
    private String cardPan;
    private String expMonth;
    private String expYear;
    private String user;
    private String country;
    private String transactionStatus;
    private String transactionCode;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "providerName", referencedColumnName = "name")
    private Provider provider;
    private String externalRef;
    @CreationTimestamp
    protected Date createdDate;
    @UpdateTimestamp
    private Date lastUpdatedOn;
}