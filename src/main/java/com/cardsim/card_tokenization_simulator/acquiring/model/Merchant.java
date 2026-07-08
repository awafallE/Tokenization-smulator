package com.cardsim.card_tokenization_simulator.acquiring.model;

import jakarta.persistence.*;
import org.hibernate.event.spi.MergeContext;

import java.util.Date;
import java.util.List;

@Entity
@Table(name ="merchant" )
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String merchantId;
    @Column(nullable = false)

    private String bankCode;
    @Column(nullable = false)

    private String companyName;
    @Column(nullable = false)

    private String doingBusinessAs;
    @Column(nullable = false)

    private String legalId;
    @Column(nullable = false)

    private String  countryCode;
    @Column(nullable = false)

    private String regionCode;
    private String       languageCode;
    private String annualTurnover;
    private String        status;
    private Date openingDate;
    private Date       createdAt;
    @OneToMany
    @JoinColumn(name = "merchantsite_id", nullable = false)
    private MerchantSite merchantSites;


    /*
    id (primary key)
merchantId (unique business identifier, like MCH001)
bankCode
companyName
doingBusinessAs
legalId
countryCode
regionCode
languageCode
annualTurnover (Long)
status (enum: PENDING, ACTIVE, SUSPENDED, CLOSED)
openingDate (LocalDate)
createdAt (LocalDateTime)
relationship to List<MerchantSite>




     */
}
