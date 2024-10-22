package com.mobilebackendfinancials.financial.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentType {
    CARD ("CARD"),
    APPLEPAY("APPLEPAY"),
    PAYPAL("PAYPAL");

    private final String typeName;
}
