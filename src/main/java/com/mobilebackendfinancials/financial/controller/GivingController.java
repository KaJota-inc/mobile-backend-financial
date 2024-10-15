package com.mobilebackendfinancials.financial.controller;

import com.mobilebackendfinancials.financial.model.dto.response.ResponseDTO;
import com.mobilebackendfinancials.financial.model.entity.Giving;
import com.mobilebackendfinancials.financial.service.GivingService;
import com.mobilebackendfinancials.financial.util.HeaderUtils;
import com.mobilebackendfinancials.financial.util.ResponseEntityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/giving")
@Slf4j
public class GivingController {

    @Autowired
    GivingService givingService;

    @GetMapping(value = "/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_KAJOTA:USER')")
    public ResponseEntity<ResponseDTO<Giving>> fetchGivingTransaction(
            @PathVariable String reference,
            HttpServletRequest httpServlet,
            Principal principal
    ) {
        String ipAddress = HeaderUtils.getClientIp(httpServlet);
        log.info("Inside fetch giving Controller with payload ::  from {} by principal {}",
                ipAddress, principal);

        return ResponseEntityUtils.getSuccessfulControllerResponse(givingService.fetchGiving(reference));
    }

    @GetMapping(value = "all/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_KAJOTA:USER')")
    public ResponseEntity<ResponseDTO<List<Giving>>> fetchAllGivingTransaction(
            @PathVariable String userId,
            HttpServletRequest httpServlet,
            Principal principal
    ) {
        String ipAddress = HeaderUtils.getClientIp(httpServlet);
        log.info("Inside fetch all giving Controller with payload ::  from {} by principal {} with {}",
                ipAddress, principal, userId);

        return ResponseEntityUtils.getSuccessfulControllerResponse(givingService.fetchAllGivings(userId));
    }
}
