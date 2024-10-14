package com.mobilebackendfinancial.financial.model.dto.request;

import com.mobilebackendfinancial.financial.constant.OrderStatus;
import com.mobilebackendfinancial.financial.model.entity.Order;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Accessors(chain = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateOrderStatusDto {

    @Accessors(chain = true)
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class RequestDto{
        private Long id;
        @NotNull(message="order id cannot be empty")
        private Long orderId;
        @NotNull(message = "prevStatus cannot be empty")
        private OrderStatus prevStatus;
    }

    @Accessors(chain = true)
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ResponseDto{
        private Order updatedOrder;
    }
}
