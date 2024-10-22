package com.mobilebackendfinancials.financial.model.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Accessors(chain = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddFundingActivitiesDto {

    @Accessors(chain = true)
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RequestDto{

        private Long id;

        @NotBlank(message = "userId cannot be blank")
        private String userId;

        @NotNull(message = "uri cannot be null")
        private String uri;

        @NotNull(message = "amount cannot be null")
        private BigDecimal amount;

        @NotBlank(message = "transactionType cannot be blank")
        private String transactionType; //Credit or Debit

        @NotNull(message = "coSold cannot be null")
        private boolean coSold; //True or False

        @NotBlank(message = "title cannot be blank")
        private String title;

        @NotBlank(message = "ccy cannot be blank")
        private String ccy;

    }

    @Accessors(chain = true)
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ResponseDto{
        private String message;
    }
}
