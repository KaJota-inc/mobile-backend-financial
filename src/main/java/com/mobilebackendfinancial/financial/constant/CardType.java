package com.mobilebackendfinancial.financial.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CardType {
    VISA("VISA"),
    MASTERCARD("MASTERCARD"),
    AMEX("AMEX");

    private String cardType;
}
