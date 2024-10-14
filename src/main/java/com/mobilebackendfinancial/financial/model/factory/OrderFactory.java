package com.mobilebackendfinancial.financial.model.factory;

import com.mobilebackendfinancial.financial.constant.OrderStatus;
import com.mobilebackendfinancial.financial.model.dto.request.PlaceOrderDto;
import com.mobilebackendfinancial.financial.model.entity.Order;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

public class OrderFactory {
    public static Order fromDto(PlaceOrderDto.RequestDto requestDto){
        return Order.builder()
                .ccy(requestDto.getCcy())
                .recipientPhoneNumber(requestDto.getRecipientPhoneNumber())
                .shippingAddress(requestDto.getShippingAddress())
                .userId(requestDto.getUserId())
                .price(requestDto.getPrice())
                .status(OrderStatus.COLLECTED)
                .orderStatusChangeTime(Instant.now())
                .orderRating(0.0)
                .orderDate(LocalDate.now())
                .orderTime(LocalTime.now())
                .build();
    }
}
