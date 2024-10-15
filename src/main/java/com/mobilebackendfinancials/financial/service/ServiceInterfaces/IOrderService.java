package com.mobilebackendfinancials.financial.service.ServiceInterfaces;

import com.mobilebackendfinancials.financial.model.dto.request.FetchOrderDto;
import com.mobilebackendfinancials.financial.model.dto.request.FetchUserOrdersDto;
import com.mobilebackendfinancials.financial.model.dto.request.PlaceOrderDto;
import com.mobilebackendfinancials.financial.model.dto.request.UpdateOrderStatusDto;

public interface IOrderService {
    PlaceOrderDto.ResponseDto placeOrder(PlaceOrderDto.RequestDto requestDto);
    FetchUserOrdersDto.ResponseDto fetchUserOrders(FetchUserOrdersDto.RequestDto requestDto);
    FetchOrderDto.ResponseDto fetchOrder(FetchOrderDto.RequestDto requestDto);
    UpdateOrderStatusDto.ResponseDto updateOrderStatus(UpdateOrderStatusDto.RequestDto requestDto);
}
