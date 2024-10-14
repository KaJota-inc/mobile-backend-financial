package com.mobilebackendfinancial.financial.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TransactionStatus {
    SUCCESS("000", "SUCCESS"),
    TIMEOUT("444", "TIMEOUT"),
    DISABLE("333", "DISABLE"),
    PENDING("555", "PENDING"),
    FAILED("999", "FAILED");

    private final String code;
    private final String message;
}
