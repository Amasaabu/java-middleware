package com.sample.middleware.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(indexes =
        {@Index(name = "tag", columnList = "tag")})
public class APIKey {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int id;
    @Column(unique = true)
    private String value;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "merchant", referencedColumnName = "id")
    private MerchantDetails merchant;
    private boolean isActive;
    @CreatedDate
    protected Date createdDate;
    @UpdateTimestamp
    private Date lastUpdatedOn;

}
