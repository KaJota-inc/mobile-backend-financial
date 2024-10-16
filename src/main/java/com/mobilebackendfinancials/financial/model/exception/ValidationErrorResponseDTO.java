package com.mobilebackendfinancials.financial.model.exception;


import com.mobilebackendfinancials.financial.constant.ResponseCodes;
import com.mobilebackendfinancials.financial.model.dto.response.ErrorResponseDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ValidationErrorResponseDTO extends ErrorResponseDTO {

    private List<FieldErrorDTO> fieldErrors;

    public ValidationErrorResponseDTO(ResponseCodes responseCode, String message, List<FieldErrorDTO> fieldErrors) {
        super(responseCode, message);
        this.fieldErrors = fieldErrors;
    }

    public ValidationErrorResponseDTO(ResponseCodes responseCode, String message, List<FieldErrorDTO> fieldErrors, String debugMessage) {
        super(responseCode, message, debugMessage);
        this.fieldErrors = fieldErrors;
    }

}
