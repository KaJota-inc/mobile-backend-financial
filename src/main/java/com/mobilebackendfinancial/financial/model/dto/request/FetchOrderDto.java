package com.mobilebackendfinancial.financial.model.dto.request;

import com.bibleappmobile.financial.model.entity.Order;
import com.bibleappmobile.financial.model.entity.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Accessors(chain = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FetchOrderDto {

    @Accessors(chain = true)
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class RequestDto{
        private String id;

        @NotNull(message = "order id cannot be null")
        private Long orderId;
    }

    @Accessors(chain = true)
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ResponseDto{
        private Order order;
    }
}
