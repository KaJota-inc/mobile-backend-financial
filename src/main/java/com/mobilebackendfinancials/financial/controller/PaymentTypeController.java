//package com.mobilebackendfinancials.financial.controller;
//
//import com.mobilebackendfinancials.financial.util.HeaderUtils;
//import com.mobilebackendfinancials.financial.util.ResponseEntityUtils;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;
//import java.security.Principal;
//
//@RestController
//@Slf4j
//@RequiredArgsConstructor
//@RequestMapping("/payment-type")
//public class PaymentTypeController {
//
//    @Autowired
//    PaymentTypeService paymentTypeService;
//
//    @PostMapping
////    @PreAuthorize("hasRole('ROLE_KAJOTA:ADMIN')")
//    public ResponseEntity<ResponseDTO<AddPaymentTypeDto.ResponseDto>> addPaymentType(
//            @RequestBody @Valid AddPaymentTypeDto.RequestDto requestDto,
//            Principal principal,
//            HttpServletRequest httpServlet
//    )
//    {
//        String ipAddress = HeaderUtils.getClientIp(httpServlet);
//        log.info("Inside add payment type with payload ::  from {} by principal {} with {}",
//                ipAddress, principal, requestDto);
//
//        return ResponseEntityUtils.getSuccessfulControllerResponse(paymentTypeService.addPaymentType(requestDto));
//    }
//
//    @GetMapping
////    @PreAuthorize("hasRole('ROLE_KAJOTA:ADMIN')")
//    public ResponseEntity<ResponseDTO<FetchPaymentTypeDto.ResponseDto>> fetchPaymentType(
//            HttpServletRequest httpServlet,
//            Principal principal
//    ){
//        String ipAddress = HeaderUtils.getClientIp(httpServlet);
//        log.info("Inside fetch payment types  Controller with payload ::  from {} by principal {}",
//                ipAddress, principal);
//
//        return ResponseEntityUtils.getSuccessfulControllerResponse( paymentTypeService.fetchPaymentTypes());
//    }
//}
