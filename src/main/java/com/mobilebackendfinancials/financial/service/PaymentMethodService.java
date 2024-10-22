package com.mobilebackendfinancials.financial.service;

import com.mobilebackendfinancials.financial.model.dto.request.AddPaymentMethodDto;
import com.mobilebackendfinancials.financial.model.dto.request.FetchPaymentMethodsDto;
import com.mobilebackendfinancials.financial.service.ServiceInterfaces.IPaymentMethodService;
import org.springframework.stereotype.Service;

@Service
public class PaymentMethodService implements IPaymentMethodService {
    @Override
    public AddPaymentMethodDto.ResponseDto addPaymentMethod(AddPaymentMethodDto.RequestDto requestDto) {
        return null;
    }

    @Override
    public FetchPaymentMethodsDto.ResponseDto addPaymentMethod() {
        return null;
    }
}
