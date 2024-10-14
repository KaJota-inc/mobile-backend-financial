package com.mobilebackendfinancial.financial.constant;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatus {

    COLLECTED("Collected", "Order from"),
    PICKEDUP("Picked Up", "Order has been picked up"),
    INTRANSIT("In Transit", "Order in transit"),
    DELIVERED("Delivered", "Order delivered");

    private final String orderStatus;
    private final String statusMessage;
}