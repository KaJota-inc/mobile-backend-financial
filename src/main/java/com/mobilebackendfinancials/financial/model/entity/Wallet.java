package com.mobilebackendfinancials.financial.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
//import javax.smartcardio.Card;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "WALLET")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "totalBalance", nullable = false)
    private BigDecimal totalBalance;

    @Column(name = "walletCurrency", nullable = false)
    private String walletCcy;

}
