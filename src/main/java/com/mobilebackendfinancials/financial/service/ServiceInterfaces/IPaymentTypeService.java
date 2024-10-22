package com.mobilebackendfinancials.financial.service.ServiceInterfaces;

import com.mobilebackendfinancials.financial.model.dto.request.AddPaymentMethodDto;
import com.mobilebackendfinancials.financial.model.dto.request.AddPaymentTypeDto;
import com.mobilebackendfinancials.financial.model.dto.request.FetchPaymentTypeDto;

public interface IPaymentTypeService {
    AddPaymentTypeDto.ResponseDto addPaymentType(AddPaymentTypeDto.RequestDto requestDto);
    FetchPaymentTypeDto.ResponseDto fetchPaymentTypes();
}
