package com.mobilebackendfinancials.financial.model.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mobilebackendfinancials.financial.model.entity.FundingActivities;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Accessors(chain = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddPaymentMethodDto {

    @Accessors(chain = true)
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RequestDto{

        private Long id;

        @NotBlank(message = "typeName cannot be blank")
        private String typeName;

        @NotBlank(message = "typeDescription cannot be blank")
        private String typeDescription;
    }

    @Accessors(chain = true)
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ResponseDto{
        private String message;
    }
}
