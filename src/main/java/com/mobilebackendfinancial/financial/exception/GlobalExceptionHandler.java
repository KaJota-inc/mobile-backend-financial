package com.mobilebackendfinancial.financial.exception;


import com.mobilebackendfinancial.financial.constant.ResponseCodes;
import com.mobilebackendfinancial.financial.model.dto.response.ErrorResponseDTO;
import com.mobilebackendfinancial.financial.model.exception.FieldErrorDTO;
import com.mobilebackendfinancial.financial.model.exception.ValidationErrorResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(
//            MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body(new ValidationErrorResponseDTO(ResponseCodes.VALIDATION_ERROR, "Invalid Inputs", (List<FieldErrorDTO>) errors));
//    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        log.error(" occurred while processing the request: {} because: {}", request.getRequestURI(),
                ex.getMessage());
        List<FieldErrorDTO> fieldErrors = ex.getFieldErrors().stream().map(
                        err -> new FieldErrorDTO(err.getField(), err.getDefaultMessage())
                )
                .collect(Collectors.toList());
        ValidationErrorResponseDTO error = new ValidationErrorResponseDTO(ResponseCodes.BAD_DATA,
                "Validation exception occurred while trying to process request", fieldErrors
//                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(GenericException ex) {
        log.error("generic exception occurred while trying to process request: {}", ex.getMessage());

        return ResponseEntity.status((ex.getHttpStatus() == null) ? HttpStatus.INTERNAL_SERVER_ERROR : ex.getHttpStatus())
                .body(new ErrorResponseDTO(
                                ex.getCode(),
                                (ex.getMessage() == null) ? ex.getCode().getMessage() : ex.getMessage(),
                                ex.getDebugStuff()
                        )
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handle(Exception ex, HttpServletRequest request,
                                                   HttpServletResponse response) {
        log.error("Exception occurred while processing the request: {} because: {}", request.getRequestURI(),
                ex.getMessage(), ex);
        return new ResponseEntity<ErrorResponseDTO>(new ErrorResponseDTO(
                ResponseCodes.PROCESS_ERROR,
                "An unknown exception just occurred. Please try again or contact administrator",
                ex.getMessage()
        ), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
