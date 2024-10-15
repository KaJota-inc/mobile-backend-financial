package com.mobilebackendfinancials.financial.service;

import com.mobilebackendfinancials.financial.constant.ResponseCodes;
import com.mobilebackendfinancials.financial.exception.GenericException;
import com.mobilebackendfinancials.financial.model.extDto.request.PaystackVerifyDto;
import com.mobilebackendfinancials.financial.util.RestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaystackBreakService {
    private final Environment env;
    @Autowired
    RestService restService;
    @Autowired
    HttpHeaders headers;

    public PaystackVerifyDto.ResponseDto verifyPaystackPayment(String reference) {
        log.info("Verifying Paystack Payment...{}", reference);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(Objects.requireNonNull(env.getProperty("paystack.secret-key")));
        String verifyUrl = env.getProperty("paystack.verify-url");
        verifyUrl = verifyUrl.replace("{reference}", reference);
        try {
            PaystackVerifyDto.ResponseDto response = restService.makeRequest(verifyUrl, HttpMethod.GET,
                    null, PaystackVerifyDto.ResponseDto.class, headers);
            log.info("Paystack Verification returns response: {}", response);
            return response;
        } catch (HttpClientErrorException ex) {
            log.error("HttpClientErrorException occurred while trying to verify paystack payment because: {}", ex.getLocalizedMessage(), ex);
            throw new GenericException(
                    ResponseCodes.PROCESS_ERROR,
                    "Third Party Error",
                    HttpStatus.resolve(Integer.parseInt(String.valueOf(ex.getStatusCode().value()))),
                    new JSONObject(ex.getResponseBodyAsString()).toMap()
            );
        } catch (Exception ex) {
            log.error("Exception occurred while trying to verify paystack payment because: {}", ex.getMessage(), ex);
            throw new GenericException(
                    ResponseCodes.PROCESS_ERROR,
                    "Third Party Error",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    ex.getMessage()
            );
        }

    }

}
