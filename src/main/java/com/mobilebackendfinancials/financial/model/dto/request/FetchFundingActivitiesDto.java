package com.mobilebackendfinancials.financial.model.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mobilebackendfinancials.financial.model.entity.FundingActivities;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Accessors(chain = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FetchFundingActivitiesDto {

    @Accessors(chain = true)
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RequestDto{

        private Long id;

        @NotBlank(message = "userId cannot be blank")
        private String userId;
    }

    @Accessors(chain = true)
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ResponseDto{
        private List<FundingActivities> fundingActivities;
    }
}
