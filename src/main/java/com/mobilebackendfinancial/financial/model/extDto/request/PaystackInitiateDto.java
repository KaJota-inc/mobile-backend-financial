package com.mobilebackendfinancial.financial.model.extDto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
public class PaystackInitiateDto {

    @Data
    @Accessors(chain = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class RequestDto {
        @NotBlank(message = "email cannot be empty")
        private String email;
        @NotBlank(message = "amount cannot be empty")
        private String amount;

        private String currency;

        @NotBlank(message = "reference cannot be empty")
        private String reference;
//        @NotBlank(message = "transaction type cannot be empty")
//        private TransactionType transactionType;

        @NotBlank(message = "userId cannot be empty")
        private String userId;


    }

    @Data
    @Accessors(chain = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ResponseDto {

        private boolean status;

        private String message;

        private DataDto data;


    }

    @Data
    @Accessors(chain = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class DataDto {

        private String authorization_url;

        private String access_code;

        private String reference;

    }

}
