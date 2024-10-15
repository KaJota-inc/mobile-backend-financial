package com.mobilebackendfinancials.financial.model.dto.response;



import com.mobilebackendfinancials.financial.constant.ResponseCodes;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

import java.util.List;

@Data
@NoArgsConstructor
public class ErrorResponseDTO {

    private String message;
    private String responseCode;
    private String debugMessage;
    private JSONObject debugObject;
    private List<?> debugArray;

    public ErrorResponseDTO(ResponseCodes responseCode, String errorMessage, Object debugStuff) {
        this.responseCode = responseCode.getCode();
        this.message = errorMessage;

        if (debugStuff instanceof String) {
            this.debugMessage = (String) debugStuff;
        }
        if (debugStuff instanceof JSONObject) {
            this.debugObject = (JSONObject) debugStuff;
        }
        if (debugStuff instanceof List) {
            this.debugArray = (List<?>) debugStuff;
        }

    }

    public ErrorResponseDTO(ResponseCodes responseCode, String errorMessage) {
        this.responseCode = responseCode.getCode();
        this.message = errorMessage;

    }

}
