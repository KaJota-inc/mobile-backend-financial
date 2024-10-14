package com.mobilebackendfinancial.financial.model.dto.request;


import com.mobilebackendfinancial.financial.constant.SubscriptionStatus;
import com.mobilebackendfinancial.financial.constant.SubscriptionType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FetchLiveSubscriptionDto {

    @Data
    @Accessors(chain = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ResponseDto {

        private SubscriptionType subscriptionType;
        private LocalDateTime dateOfNextSubscription;
        private SubscriptionStatus subscriptionStatus;
        private String currency;
        private BigDecimal amountPaid;
        private String paymentMethod;


    }
}
