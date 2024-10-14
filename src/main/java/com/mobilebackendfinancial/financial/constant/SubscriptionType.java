package com.mobilebackendfinancial.financial.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SubscriptionType {

    ANNUALLY(12, "ANNUALLY"),
    QUARTERLY(3, "QUARTERLY"),
    MONTHLY(1, "MONTHLY");

    private final int noOfMonths;
    private final String message;

}
