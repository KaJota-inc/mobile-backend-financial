package com.mobilebackendfinancials.financial.controller;


import com.mobilebackendfinancials.financial.model.dto.request.FetchOrderDto;
import com.mobilebackendfinancials.financial.model.dto.request.FetchUserOrdersDto;
import com.mobilebackendfinancials.financial.model.dto.request.PlaceOrderDto;
import com.mobilebackendfinancials.financial.model.dto.request.UpdateOrderStatusDto;
import com.mobilebackendfinancials.financial.model.dto.response.ResponseDTO;
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
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
//    @PreAuthorize("hasRole('ROLE_KAJOTA:USER')")
    public ResponseEntity<ResponseDTO<PlaceOrderDto.ResponseDto>> placeOrder(
            @RequestBody @Valid PlaceOrderDto.RequestDto requestDto,
            Principal principal,
            HttpServletRequest httpServlet
            )
    {
        String ipAddress = HeaderUtils.getClientIp(httpServlet);
        log.info("Inside place order Controller with payload ::  from {} by principal {} with {}",
                ipAddress, principal, requestDto);

        return ResponseEntityUtils.getSuccessfulControllerResponse(orderService.placeOrder(requestDto));
    }

    @GetMapping("/get-user-orders")
    @PreAuthorize("hasRole('ROLE_KAJOTA:USER')")
    public ResponseEntity<ResponseDTO<FetchUserOrdersDto.ResponseDto>> fetchAllUserOrders(
            @RequestBody @Valid FetchUserOrdersDto.RequestDto requestDto,
            HttpServletRequest httpServlet,
            Principal principal
    ){
        String ipAddress = HeaderUtils.getClientIp(httpServlet);
        log.info("Inside place order Controller with payload ::  from {} by principal {} with {}",
                ipAddress, principal, requestDto);

        return ResponseEntityUtils.getSuccessfulControllerResponse( orderService.fetchUserOrders(requestDto));
    }

    @GetMapping("/get-order")
    @PreAuthorize("hasRole('ROLE_KAJOTA:USER')")
    public ResponseEntity<ResponseDTO<FetchOrderDto.ResponseDto>> fetchAllUserOrders(
            @RequestBody @Valid FetchOrderDto.RequestDto requestDto,
            HttpServletRequest httpServlet,
            Principal principal
    ){
        String ipAddress = HeaderUtils.getClientIp(httpServlet);
        log.info("Inside place order Controller with payload ::  from {} by principal {} with {}",
                ipAddress, principal, requestDto);

        return ResponseEntityUtils.getSuccessfulControllerResponse( orderService.fetchOrder(requestDto));
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_KAJOTA:USER')")
    public ResponseEntity<ResponseDTO<UpdateOrderStatusDto.ResponseDto>> changeOrderStatus(
            @RequestBody @Valid UpdateOrderStatusDto.RequestDto requestDto,
            HttpServletRequest httpServlet,
            Principal principal
    ){
        String ipAddress = HeaderUtils.getClientIp(httpServlet);
        log.info("Inside update order status Controller with payload ::  from {} by principal {} with {}",
                ipAddress, principal, requestDto);

        return ResponseEntityUtils.getSuccessfulControllerResponse( orderService.updateOrderStatus(requestDto));
    }
}
