package com.mobilebackendfinancials.financial.model.entity;

import com.mobilebackendfinancials.financial.constant.SubscriptionStatus;
import com.mobilebackendfinancials.financial.constant.SubscriptionType;
import com.mobilebackendfinancials.financial.constant.TransactionStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@Table(name = "SUBSCRIPTION")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "reference", unique = true, nullable = false)
    private String reference;
    @Column(name = "amount", nullable = false)
    private String amount;
    @Column(name = "currency", nullable = false)
    private String currency;
    @Column(name = "userId", nullable = false)
    private String userId;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionStatus status = TransactionStatus.PENDING;
    @Column(name = "message")
    private String message;
    @Column(name = "channel", nullable = false)
    private String channel = "bank";
    @Column(name = "fees")
    private Long fees;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "paidAt")
    private LocalDateTime paidAt;
    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;
    @Column(name = "createdAt")
    private LocalDateTime createdAt;
    @Column(name = "access_code", nullable = false)
    private String access_code;
    @Column(name = "subscriptionType", nullable = false)
    @Enumerated(EnumType.STRING)
    private SubscriptionType subscriptionType = SubscriptionType.MONTHLY;
    @Enumerated(EnumType.STRING)
    @Column(name = "subscriptionStatus", nullable = false)
    private SubscriptionStatus subscriptionStatus = SubscriptionStatus.DISABLED;

    @CreationTimestamp
    @Column(name = "CREATED_DATE", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @PrePersist
    public void dateAddedBeforeInsertion() {
        this.createdDate = LocalDateTime.now();
    }


}
