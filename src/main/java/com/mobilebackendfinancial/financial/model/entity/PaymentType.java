package com.mobilebackendfinancial.financial.model.entity;

import javax.persistence.*;

@Entity
public class PaymentType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long  id;

    @Column(name = "payment_type_name", nullable = false)
    private String typeName;
}
