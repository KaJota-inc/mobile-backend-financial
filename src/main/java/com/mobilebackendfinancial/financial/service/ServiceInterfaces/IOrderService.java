package com.mobilebackendfinancial.financial.service.ServiceInterfaces;

import com.mobilebackendfinancial.financial.model.dto.request.FetchOrderDto;
import com.mobilebackendfinancial.financial.model.dto.request.FetchUserOrdersDto;
import com.mobilebackendfinancial.financial.model.dto.request.PlaceOrderDto;
import com.mobilebackendfinancial.financial.model.dto.request.UpdateOrderStatusDto;

public interface IOrderService {
    PlaceOrderDto.ResponseDto placeOrder(PlaceOrderDto.RequestDto requestDto);
    FetchUserOrdersDto.ResponseDto fetchUserOrders(FetchUserOrdersDto.RequestDto requestDto);
    FetchOrderDto.ResponseDto fetchOrder(FetchOrderDto.RequestDto requestDto);
    UpdateOrderStatusDto.ResponseDto updateOrderStatus(UpdateOrderStatusDto.RequestDto requestDto);
}
