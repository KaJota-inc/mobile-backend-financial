package com.mobilebackendfinancial.financial.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
@Data
@Accessors(chain = true)
@Table(name = "FUNDING_ACTIVITIES")
public class FundingActivities {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "uri", nullable = false)
    private String uri;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "time", nullable = false)
    private LocalTime time;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "transaction_type", nullable = false)
    private String transactionType; //Credit or Debit

    @Column(name = "co_sold", nullable = false)
    private boolean coSold; //True or False

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "currency", nullable = false)
    private String currency;

}
