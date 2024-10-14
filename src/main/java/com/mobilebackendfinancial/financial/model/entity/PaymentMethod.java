package com.mobilebackendfinancial.financial.model.entity;

import com.mobilebackendfinancial.financial.constant.CardType;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "payment_type_id", nullable = false)
    private String paymentTypeId;

    @Column(name = "provider")
    private String provider;

    @Column(name = "card_type")
    private CardType cardType;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

}
