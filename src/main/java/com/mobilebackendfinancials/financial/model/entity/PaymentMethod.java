//package com.mobilebackendfinancials.financial.model.entity;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.mobilebackendfinancials.financial.constant.CardType;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.NoArgsConstructor;
//import lombok.experimental.Accessors;
//import org.codehaus.jackson.annotate.JsonIgnore;
//
//import javax.persistence.*;
//
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@Accessors(chain = true)
//@Builder
//public class PaymentMethod {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    private Long id;
//
//    @JsonIgnore
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_id", nullable = false)
//    @JsonBackReference
//    private Order order;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "payment_type", nullable = false)
//    private PaymentType paymentType; // e.g Card, Paypal, Apple etc
//
//    @Column(name = "provider")
//    private String provider; //Optional
//
//    @Column(name = "card_type")
//    private CardType cardType; //Optional
//
//    @Column(name = "account_Id", nullable = false)
//    private String accountId;
//
//}
