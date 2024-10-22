package com.mobilebackendfinancials.financial.service;

import com.mobilebackendfinancials.financial.model.dto.request.AddPaymentTypeDto;
import com.mobilebackendfinancials.financial.model.dto.request.FetchPaymentTypeDto;
import com.mobilebackendfinancials.financial.model.entity.PaymentType;
import com.mobilebackendfinancials.financial.repository.PaymentTypeRepository;
import com.mobilebackendfinancials.financial.service.ServiceInterfaces.IPaymentTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PaymentTypeService implements IPaymentTypeService {

    @Autowired
    PaymentTypeRepository paymentTypeRepository;

    @Override
    public AddPaymentTypeDto.ResponseDto addPaymentType(AddPaymentTypeDto.RequestDto requestDto) {
        try {
            PaymentType newPaymentType = new PaymentType()
                    .setTypeName(requestDto.getTypeName())
                    .setTypeDescription(requestDto.getTypeDescription());

            log.info("This is the new payment type: {}", newPaymentType);
            paymentTypeRepository.save(newPaymentType);
            return new AddPaymentTypeDto.ResponseDto().setMessage("Successfully added a payment type");
        } catch (Exception ex) {
            log.info("Something went wrong while adding Payment type: {}", ex.getMessage());
        }
        return null;
    }

    @Override
    public FetchPaymentTypeDto.ResponseDto fetchPaymentTypes() {
        try {
            List<PaymentType> paymentTypes = paymentTypeRepository.findAll();
            log.info("This is the new payment type: {}", paymentTypes);
            return new FetchPaymentTypeDto.ResponseDto().setPaymentTypes(paymentTypes);
        } catch (Exception ex) {
            log.info("Something went wrong while fetching payment types: {}", ex.getMessage());
        }
        return null;
    }
}
