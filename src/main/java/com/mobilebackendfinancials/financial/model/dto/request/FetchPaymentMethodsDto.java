package com.mobilebackendfinancials.financial.model.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mobilebackendfinancials.financial.model.entity.FundingActivities;
import com.mobilebackendfinancials.financial.model.entity.PaymentMethod;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Accessors(chain = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FetchPaymentMethodsDto {

    @Accessors(chain = true)
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ResponseDto{
        private List<PaymentMethod> paymentMethods;
    }
}
