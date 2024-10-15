package com.mobilebackendfinancials.financial.model.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO<T> {

    private String message;
    private String responseCode;
    private T payload;


}
