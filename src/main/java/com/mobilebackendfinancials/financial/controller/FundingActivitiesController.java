package com.mobilebackendfinancials.financial.controller;

import com.mobilebackendfinancials.financial.model.dto.request.AddFundingActivitiesDto;
import com.mobilebackendfinancials.financial.model.dto.request.FetchFundingActivitiesDto;
import com.mobilebackendfinancials.financial.model.dto.request.FetchUserOrdersDto;
import com.mobilebackendfinancials.financial.model.dto.request.PlaceOrderDto;
import com.mobilebackendfinancials.financial.model.dto.response.ResponseDTO;
import com.mobilebackendfinancials.financial.service.FundingActivitiesService;
import com.mobilebackendfinancials.financial.service.OrderService;
import com.mobilebackendfinancials.financial.util.HeaderUtils;
import com.mobilebackendfinancials.financial.util.ResponseEntityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/funding-activities")
public class FundingActivitiesController {

    @Autowired
    FundingActivitiesService fundingActivitiesService;

    @PostMapping
//    @PreAuthorize("hasRole('ROLE_KAJOTA:USER')")
    public ResponseEntity<ResponseDTO<AddFundingActivitiesDto.ResponseDto>> addFundingActivity(
            @RequestBody @Valid AddFundingActivitiesDto.RequestDto requestDto,
            Principal principal,
            HttpServletRequest httpServlet
    )
    {
        String ipAddress = HeaderUtils.getClientIp(httpServlet);
        log.info("Inside add funding activity with payload ::  from {} by principal {} with {}",
                ipAddress, principal, requestDto);

        return ResponseEntityUtils.getSuccessfulControllerResponse(fundingActivitiesService.addFundingActivities(requestDto));
    }

    @GetMapping
//    @PreAuthorize("hasRole('ROLE_KAJOTA:USER')")
    public ResponseEntity<ResponseDTO<FetchFundingActivitiesDto.ResponseDto>> fetchUserFundingActivities(
            @RequestBody @Valid FetchFundingActivitiesDto.RequestDto requestDto,
            HttpServletRequest httpServlet,
            Principal principal
    ){
        String ipAddress = HeaderUtils.getClientIp(httpServlet);
        log.info("Inside place order Controller with payload ::  from {} by principal {} with {}",
                ipAddress, principal, requestDto);

        return ResponseEntityUtils.getSuccessfulControllerResponse( fundingActivitiesService.fetchFundingActivities(requestDto));
    }
}
