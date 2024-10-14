package com.mobilebackendfinancial.financial.model.dto.request;

import com.mobilebackendfinancial.financial.model.entity.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceOrderDto {

    @Data
    @Accessors(chain = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RequestDto{
        @NotBlank(message = "user id cannot be blank")
        private String userId;
        @NotBlank(message = "shipping address cannot be blank")
        private String shippingAddress;
        @NotBlank(message = "recipient phone number cannot be blank")
        private String recipientPhoneNumber;
        @NotNull(message = "user id cannot be null")
        private List<OrderItem> orderItems;
        @NotNull(message = "price cannot be null")
        private BigDecimal price;
        @NotBlank(message = "currency cannot be blank")
        private String ccy;
    }

    @Data
    @Accessors(chain = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ResponseDto{
        private String message;
    }
}
