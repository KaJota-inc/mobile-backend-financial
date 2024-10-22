package com.mobilebackendfinancials.financial.service.ServiceInterfaces;

import com.mobilebackendfinancials.financial.model.dto.request.AddPaymentMethodDto;
import com.mobilebackendfinancials.financial.model.dto.request.FetchPaymentMethodsDto;

public interface IPaymentMethodService {
    AddPaymentMethodDto.ResponseDto addPaymentMethod(AddPaymentMethodDto.RequestDto requestDto);
    FetchPaymentMethodsDto.ResponseDto addPaymentMethod();
}
