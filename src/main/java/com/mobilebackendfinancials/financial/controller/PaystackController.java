package com.mobilebackendfinancials.financial.controller;


import com.mobilebackendfinancials.financial.model.dto.request.PaymentInitiationDto;
import com.mobilebackendfinancials.financial.model.dto.response.ResponseDTO;
import com.mobilebackendfinancials.financial.model.extDto.request.PaystackInitiateDto;
import com.mobilebackendfinancials.financial.model.extDto.request.PaystackVerifyDto;
import com.mobilebackendfinancials.financial.service.PaystackBreakService;
import com.mobilebackendfinancials.financial.service.PaystackService;
import com.mobilebackendfinancials.financial.util.HeaderUtils;
import com.mobilebackendfinancials.financial.util.ResponseEntityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/paystack")
@Slf4j
public class PaystackController {

    @Autowired
    PaystackService paystackService;

    @Autowired
    PaystackBreakService paystackBreakService;

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_BAM:USER')")
    public ResponseEntity<ResponseDTO<PaystackInitiateDto.ResponseDto>> initiatePayment(
            @RequestBody @Valid PaymentInitiationDto.RequestDto requestDto,
            HttpServletRequest httpServlet,
            Principal principal
    ) {
        String ipAddress = HeaderUtils.getClientIp(httpServlet);
        log.info("Inside Initiate Paystack Controller with payload :: {} from {} by principal {}", new JSONObject(requestDto),
                ipAddress, principal);

        return ResponseEntityUtils.getSuccessfulControllerResponse(paystackService.initiatePaystackPayment(requestDto));
    }

    @GetMapping(value = "/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_BAM:USER')")
    public ResponseEntity<ResponseDTO<PaystackVerifyDto.ResponseDto>> verifyPaystack(
            @PathVariable String reference,
            HttpServletRequest httpServlet,
            Principal principal
    ) {
        String ipAddress = HeaderUtils.getClientIp(httpServlet);
        log.info("Inside Verify paystack Controller with payload ::  from {} by principal {}",
                ipAddress, principal);

        return ResponseEntityUtils.getSuccessfulControllerResponse(paystackBreakService.verifyPaystackPayment(reference));
    }

    @PostMapping(value = "callback", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    @PreAuthorize("hasRole('ROLE_BAM:USER')")
    public ResponseEntity<ResponseDTO<Boolean>> callbackPost(
            @RequestBody @Valid PaystackVerifyDto.ResponseDto requestDto,
            HttpServletRequest httpServlet,
            Principal principal
    ) {
        String ipAddress = HeaderUtils.getClientIp(httpServlet);
        log.info("Inside Paystack Callback Post Controller with payload :: {} from {} by principal {}", new JSONObject(requestDto),
                ipAddress, principal);

        return ResponseEntityUtils.getSuccessfulControllerResponse(paystackService.callbackPost(requestDto));
    }

    @GetMapping(value = "callback", produces = MediaType.APPLICATION_JSON_VALUE)
//    @PreAuthorize("hasRole('ROLE_BAM:USER')")
    public ResponseEntity<ResponseDTO<Object>> callbackFetch(
            @RequestParam String trxref,
            @RequestParam String reference,
            HttpServletRequest httpServlet,
            Principal principal
    ) {
        String ipAddress = HeaderUtils.getClientIp(httpServlet);
        log.info("Inside Verify paystack Controller with payload ::  from {} by principal {} by {} and {}",
                ipAddress, principal, trxref, reference);

        return ResponseEntityUtils.getSuccessfulControllerResponse(paystackService.callbackGet(trxref, reference));
    }

}
