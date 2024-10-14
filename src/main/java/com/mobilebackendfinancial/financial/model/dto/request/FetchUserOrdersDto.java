package com.mobilebackendfinancial.financial.model.dto.request;

import com.bibleappmobile.financial.model.entity.Order;
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
public class FetchUserOrdersDto {

    @Accessors(chain = true)
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class RequestDto{
        private String id;

        @NotBlank(message = "user id cannot be blank")
        private String userId;

    }

    @Accessors(chain = true)
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ResponseDto{
        private List<Order> orders;
    }
}
