package com.mobilebackendfinancials.financial.service;


import com.mobilebackendfinancials.financial.constant.ResponseCodes;
import com.mobilebackendfinancials.financial.constant.TransactionStatus;
import com.mobilebackendfinancials.financial.exception.GenericException;
import com.mobilebackendfinancials.financial.model.entity.Giving;
import com.mobilebackendfinancials.financial.model.extDto.request.PaystackVerifyDto;
import com.mobilebackendfinancials.financial.repository.GivingRepository;
import com.mobilebackendfinancials.financial.util.Misc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GivingService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    GivingRepository givingRepository;

    @Autowired
    PaystackBreakService paystackBreakService;


    public Giving addGivingTransaction(Giving giving) {
        log.info("Trying to add GIVING transaction");

        Giving savedGiving = givingRepository.save(giving);

        log.info("savedGiving:  {}", new JSONObject(savedGiving));
        return savedGiving;

    }

    public Giving updateGivingTransaction(PaystackVerifyDto.ResponseDto callbackResponse) {
        log.info("Trying to update GIVING transaction");
        PaystackVerifyDto.DataDto paystackData = callbackResponse.getData();

        BigDecimal realAmount = new BigDecimal(paystackData.getAmount()).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);

        String callbackStatus = Misc.getStatusFromEvent(callbackResponse.getEvent());
        TransactionStatus status = TransactionStatus.valueOf(callbackStatus);

        Optional<Giving> optGiving = givingRepository.findByReference(paystackData.getReference());
        Giving giving = optGiving.orElse(new Giving());
        Long givingId = giving.getId();
        String userId = giving.getUserId();
        modelMapper.map(paystackData, giving);
        giving
                .setId(givingId)
                .setUserId(userId)
                .setAmount(String.valueOf(realAmount))
                .setStatus(status)
                .setEmail(paystackData.getCustomer().getEmail())
                .setPhone(paystackData.getCustomer().getPhone())
                .setPaidAt(paystackData.getPaid_at())
                .setCreatedAt(paystackData.getCreated_at());
        Giving savedGiving = givingRepository.save(giving);

        log.info("savedGiving:  {}", new JSONObject(savedGiving));
        return savedGiving;

    }

    public List<Giving> fetchAllGivings(String userId) {

        log.info("Trying to fetch all GIVING transaction");

        return givingRepository.findByUserId(userId);

    }

    public Giving fetchGiving(String reference) {
        log.info("Trying to fetch GIVING transaction");

        Optional<Giving> optGiving = givingRepository.findByReference(reference);


        if (
                !optGiving.isPresent()
        ) {
            throw new GenericException(
                    ResponseCodes.NOT_FOUND,
                    "Transaction for reference " + reference + " does not exist",
                    HttpStatus.NOT_FOUND,
                    null
            );
        }

        Giving foundGiving = optGiving.get();


        if (TransactionStatus.PENDING.equals(foundGiving.getStatus())) {
            try {

                PaystackVerifyDto.ResponseDto responseDto = paystackBreakService.verifyPaystackPayment(reference);
                PaystackVerifyDto.DataDto paystackData = responseDto.getData();
                BigDecimal realAmount = new BigDecimal(paystackData.getAmount()).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);

                TransactionStatus status = TransactionStatus.valueOf(paystackData.getStatus().toUpperCase());

                Long givingId = foundGiving.getId();
                String userId = foundGiving.getUserId();
                modelMapper.map(paystackData, foundGiving);
                foundGiving
                        .setId(givingId)
                        .setUserId(userId)
                        .setAmount(String.valueOf(realAmount))
                        .setStatus(status)
                        .setEmail(paystackData.getCustomer().getEmail())
                        .setPhone(paystackData.getCustomer().getPhone())
                        .setPaidAt(paystackData.getPaid_at())
                        .setCreatedAt(paystackData.getCreated_at());
                foundGiving = givingRepository.save(foundGiving);
            } catch (Exception ex) {
                log.info("Error while trying to verifying payment:: {}", ex.getMessage());

            }
        }

        log.info("foundGiving:  {}", new JSONObject(foundGiving));
        return foundGiving;

    }


}
