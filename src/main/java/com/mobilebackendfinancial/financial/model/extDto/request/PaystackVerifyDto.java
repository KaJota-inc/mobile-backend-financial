package com.mobilebackendfinancial.financial.model.extDto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaystackVerifyDto {

    @Data
    @Accessors(chain = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ResponseDto {

        private String status;
        private String message;
        private String event;
        private DataDto data;
    }

    @Data
    @Accessors(chain = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DataDto {

        private String id;
        private String domain;
        private String status;
        private String reference;
        private String receipt_number;
        private String amount;
        private String message;
        private String gateway_response;
        private LocalDateTime paid_at;
        private LocalDateTime created_at;
        private String channel;
        private String currency;
        private String ip_address;
        private String metadata;
        private Long fees;
        private Log log;
        private String fees_split;
        private Authorization authorization;
        private Customer customer;
        private PlanObject plan;
        private Split split;
        private String order_id;
        private LocalDateTime paidAt;
        private LocalDateTime createdAt;
        private String requested_amount;
        private LocalDateTime pos_transaction_data;
        private Source source;
        private String fees_breakdown;
        private LocalDateTime transaction_date;
        private PlanObject plan_object;
        private SubAccount subaccount;

    }


    @Data
    @Accessors(chain = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Log {
        private Long start_time;
        private Long time_spent;
        private Long attempts;
        private String authentication;
        private String errors;
        private boolean success;
        private boolean mobile;
        private List<String> input = new ArrayList<>();
        private List<History> history = new ArrayList<>();
    }


    @Data
    @Accessors(chain = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Authorization {
        private String authorization_code;
        private String bin;
        private String last4;
        private String exp_month;
        private String exp_year;
        private String channel;
        private String card_type;
        private String bank;
        private String country_code;
        private String brand;
        private boolean reusable;
        private String signature;
        private String account_name;
    }

    @Data
    @Accessors(chain = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Customer {
        private Long id;
        private String first_name;
        private String last_name;
        private String email;
        private String customer_code;
        private String phone;
        private String metadata;
        private String risk_action;
        private String international_format_phone;

    }

    @Data
    @Accessors(chain = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Split {

    }

    @Data
    @Accessors(chain = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PlanObject {

    }

    @Data
    @Accessors(chain = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SubAccount {

    }

    @Data
    @Accessors(chain = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class History {
        private String type;
        private String message;
        private Long time;
    }

    @Data
    @Accessors(chain = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Source {

        private String type;
        private String source;
        private String entry_point;
        private String identifier;
    }
}
