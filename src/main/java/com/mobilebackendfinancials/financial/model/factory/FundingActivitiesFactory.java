package com.mobilebackendfinancials.financial.model.factory;


import com.mobilebackendfinancials.financial.model.dto.request.AddFundingActivitiesDto;
import com.mobilebackendfinancials.financial.model.entity.FundingActivities;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalTime;

public class FundingActivitiesFactory {
    public static FundingActivities fromDto(AddFundingActivitiesDto.RequestDto requestDto){
        return FundingActivities.builder()
                .amount(requestDto.getAmount())
                .userId(requestDto.getUserId())
                .title(requestDto.getTitle())
                .coSold(requestDto.isCoSold())
                .uri(requestDto.getUri())
                .transactionType(requestDto.getTransactionType())
                .ccy(requestDto.getCcy())
                .date(LocalDate.now())
                .time(LocalTime.now())
                .build();
    }
}
