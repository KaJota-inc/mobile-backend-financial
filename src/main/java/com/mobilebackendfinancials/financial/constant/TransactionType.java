package com.mobilebackendfinancials.financial.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TransactionType {
    GIVING("GIVING"),
    SUBSCRIPTION("SUBSCRIPTION");
    private final String message;
}
