package com.mobilebackendfinancial.financial.exception;


import com.mobilebackendfinancial.financial.constant.ResponseCodes;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
public class GenericException extends RuntimeException {

    private HttpStatus httpStatus;
    private ResponseCodes code;
    private String message;
    private String debugMessage;
    private Map<?, ?> debugObject;
    private List<?> debugArray;
    private Object debugStuff;


    public GenericException(ResponseCodes code, String message, HttpStatus httpStatus, Object debugStuff) {
        super(message);
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
        this.debugStuff = debugStuff;


        if (debugStuff instanceof String) {
            this.debugMessage = (String) debugStuff;
        }
        if (debugStuff instanceof Map) {
            this.debugObject = (Map<?, ?>) debugStuff;
        }
        if (debugStuff instanceof List) {
            this.debugArray = (List<?>) debugStuff;
        }
    }


}
