package com.mobilebackendfinancials.financial.model.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Accessors(chain = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddPaymentTypeDto {
    @Accessors(chain = true)
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RequestDto {

        private Long id;

        @NotBlank(message = "typeName cannot be blank")
        private String typeName;

        @NotBlank(message = "typeDescription cannot be blank")
        private String typeDescription;
    }

    @Accessors(chain = true)
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ResponseDto {
        private String message;

    }
}