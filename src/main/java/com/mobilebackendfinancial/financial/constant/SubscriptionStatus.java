package com.mobilebackendfinancial.financial.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SubscriptionStatus {
    ACTIVE("ACTIVE"),
    DISABLED("DISABLED"),
    EXPIRED("EXPIRED");

    private final String message;
}
