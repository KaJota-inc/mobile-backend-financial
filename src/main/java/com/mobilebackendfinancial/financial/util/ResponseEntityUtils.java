package com.mobilebackendfinancial.financial.util;


import com.mobilebackendfinancial.financial.constant.ResponseCodes;
import com.mobilebackendfinancial.financial.model.dto.response.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;


@Slf4j
public class ResponseEntityUtils {

    /**
     * Returns successful response for controllers with method return holders
     *
     * @param payload: the payload
     * @param <T>:     the type
     * @return the response
     */
    public static <T> ResponseEntity<ResponseDTO<T>> getSuccessfulControllerResponse(T payload) {

        return ResponseEntity.ok().body(
                new ResponseDTO<T>()
                        .setResponseCode(ResponseCodes.SUCCESS.getCode())
                        .setMessage(ResponseCodes.SUCCESS.getMessage())
                        .setPayload(payload)
        );
    }


    /**
     * Returns successful response for controllers with general response holder
     *
     * @param payload: the general responsepayload
     * @param <T>:     the type
     * @return the response
     */
    public static <T> ResponseEntity<ResponseDTO<T>> getSuccessfulControllerGeneralResponse(ResponseDTO<T> payload) {

        return ResponseEntity.ok().body(payload);
    }


}
