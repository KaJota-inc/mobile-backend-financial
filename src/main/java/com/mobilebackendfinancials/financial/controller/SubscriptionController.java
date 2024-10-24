//package com.mobilebackendfinancials.financial.controller;
//
//
//import com.mobilebackendfinancials.financial.model.dto.request.FetchLiveSubscriptionDto;
//import com.mobilebackendfinancials.financial.model.dto.response.ResponseDTO;
//import com.mobilebackendfinancials.financial.service.SubscriptionService;
//import com.mobilebackendfinancials.financial.util.HeaderUtils;
//import com.mobilebackendfinancials.financial.util.ResponseEntityUtils;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import java.security.Principal;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/subscription")
//@Slf4j
//public class SubscriptionController {
//
//    @Autowired
//    SubscriptionService subscriptionService;
//
//    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    @PreAuthorize("hasRole('ROLE_KAJOTA:USER')")
//    public ResponseEntity<ResponseDTO<FetchLiveSubscriptionDto.ResponseDto>> fetchLiveSubscription(
//            @PathVariable String userId,
//            HttpServletRequest httpServlet,
//            Principal principal
//    ) {
//        String ipAddress = HeaderUtils.getClientIp(httpServlet);
//        log.info("Inside fetch live subscription controller with payload ::  from {} by principal {}",
//                ipAddress, principal);
//
//        return ResponseEntityUtils.getSuccessfulControllerResponse(subscriptionService.fetchLiveSubscription(userId));
//    }
//}
