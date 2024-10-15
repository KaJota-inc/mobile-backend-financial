package com.mobilebackendfinancials.financial.model.dto.request;

import com.mobilebackendfinancials.financial.constant.SubscriptionStatus;
import com.mobilebackendfinancials.financial.constant.SubscriptionType;
import com.mobilebackendfinancials.financial.model.extDto.request.PaystackInitiateDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentInitiationDto {

    @Data
    @Accessors(chain = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class RequestDto extends PaystackInitiateDto.RequestDto {

        //        @NotBlank(message = "subscription type be empty")
        private SubscriptionType subscriptionType;
        //        @NotBlank(message = "subscription status cannot be empty")
        private SubscriptionStatus subscriptionStatus;

    }
}
