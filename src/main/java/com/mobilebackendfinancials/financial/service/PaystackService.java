//package com.mobilebackendfinancials.financial.service;
//
//
//import com.mobilebackendfinancials.financial.constant.ResponseCodes;
//import com.mobilebackendfinancials.financial.constant.TransactionType;
//import com.mobilebackendfinancials.financial.exception.GenericException;
//import com.mobilebackendfinancials.financial.model.dto.request.PaymentInitiationDto;
//import com.mobilebackendfinancials.financial.model.entity.Giving;
//import com.mobilebackendfinancials.financial.model.entity.Subscription;
//import com.mobilebackendfinancials.financial.model.extDto.request.PaystackInitiateDto;
//import com.mobilebackendfinancials.financial.model.extDto.request.PaystackVerifyDto;
//import com.mobilebackendfinancials.financial.repository.GivingRepository;
//import com.mobilebackendfinancials.financial.repository.SubscriptionRepository;
//import com.mobilebackendfinancials.financial.util.RestService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.json.JSONObject;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.HttpClientErrorException;
//
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.util.Objects;
//import java.util.Optional;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class PaystackService {
//    private final Environment env;
//    @Autowired
//    ModelMapper modelMapper;
//    @Autowired
//    RestService restService;
//    @Autowired
//    HttpHeaders headers;
//    @Autowired
//    GivingService givingService;
//    @Autowired
//    SubscriptionService subscriptionService;
//    @Autowired
//    GivingRepository givingRepository;
//    @Autowired
//    SubscriptionRepository subscriptionRepository;
//
//    public PaystackInitiateDto.ResponseDto initiatePaystackPayment(PaymentInitiationDto.RequestDto requestDto) {
//        log.info("Initiating Paystack Payment...{}", new JSONObject(requestDto));
//        BigDecimal paystackAmount = new BigDecimal(requestDto.getAmount()).multiply(BigDecimal.valueOf(100));
//        requestDto.setAmount(String.valueOf(paystackAmount));
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(Objects.requireNonNull(env.getProperty("paystack.secret-key")));
//        String initiateUrl = env.getProperty("paystack.initiate-url");
//
////        requestDto.setSubscriptionType(null!=requestDto.getSubscriptionType()
////                ? requestDto.getSubscriptionType().
////    :
////        );
//
//        try {
//            PaystackInitiateDto.ResponseDto response = restService.makeRequest(initiateUrl, HttpMethod.POST,
//                    requestDto, PaystackInitiateDto.ResponseDto.class, headers);
//            log.info("Paystack Initialization returns response: {}", response);
//            handleCreatingTransaction(requestDto, response);
//            return response;
//        } catch (HttpClientErrorException ex) {
//            log.error("HttpClientErrorException occurred while trying to initiate paystack initiation because: {}", ex.getLocalizedMessage(), ex);
//            throw new GenericException(
//                    ResponseCodes.PROCESS_ERROR,
//                    "Third Party Error",
//                    HttpStatus.resolve(Integer.parseInt(String.valueOf(ex.getStatusCode().value()))),
//                    ex.getResponseBodyAsString()
//            );
//        } catch (Exception ex) {
//            log.error("Exception occurred while trying to initiate paystack initiation because: {}", ex.getMessage(), ex);
//            throw new GenericException(
//                    ResponseCodes.PROCESS_ERROR,
//                    "Third Party Error",
//                    HttpStatus.INTERNAL_SERVER_ERROR,
//                    ex.getMessage()
//            );
//        }
//    }
//
//
//    public void handleCreatingTransaction(PaymentInitiationDto.RequestDto requestDto, PaystackInitiateDto.ResponseDto responseDto) {
//        log.info("Inside handle Creating Transaction");
//        String reference = requestDto.getReference();
//        TransactionType transactionType = TransactionType.valueOf(reference.split("_")[0]);
//        log.info("transactionType {} from reference {}", transactionType, reference);
//
//        BigDecimal realAmount = new BigDecimal(requestDto.getAmount()).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
//
//        switch (transactionType) {
//            case GIVING:
//                Giving giving = new Giving();
//                giving
//                        .setEmail(requestDto.getEmail())
//                        .setAmount(String.valueOf(realAmount))
//                        .setCurrency(requestDto.getCurrency())
//                        .setReference(reference)
//                        .setUserId(requestDto.getUserId())
//                        .setAccess_code(responseDto.getData().getAccess_code());
//
//                Giving addedGiving = givingService.addGivingTransaction(giving);
//                log.info("addedGiving: {}", addedGiving);
//                break;
//            case SUBSCRIPTION:
//                Subscription subscription = new Subscription();
//                subscription
//                        .setEmail(requestDto.getEmail())
//                        .setAmount(String.valueOf(realAmount))
//                        .setCurrency(requestDto.getCurrency())
//                        .setReference(reference)
//                        .setUserId(requestDto.getUserId())
//                        .setAccess_code(responseDto.getData().getAccess_code())
////                        .setSubscriptionStatus(requestDto.getSubscriptionStatus())
//                        .setSubscriptionType(requestDto.getSubscriptionType())
//                ;
//
//                Subscription addedSubscription = subscriptionService.addSubscriptionTransaction(subscription);
//                log.info("addedSubscription: {}", addedSubscription);
//                break;
//
//            default:
//                break;
//        }
//    }
//
//    public boolean callbackPost(PaystackVerifyDto.ResponseDto callbackResponse) {
//        log.info("Inside Paystack Callback Post Response...");
//
//        String reference = callbackResponse.getData().getReference();
//
//        TransactionType transactionType = TransactionType.valueOf(reference.split("_")[0].toUpperCase());
//        log.info("transactionType {} from reference {}", transactionType, reference);
//
//        switch (transactionType) {
//            case GIVING:
//                givingService.updateGivingTransaction(callbackResponse);
//                break;
//            case SUBSCRIPTION:
//                subscriptionService.updateSubscriptionTransaction(callbackResponse);
//                break;
//            default:
//                break;
//        }
//        return true;
//    }
//
//    public Object callbackGet(String trxref, String reference) {
//        log.info("Inside Paystack Callback Fetch...");
//        TransactionType transactionType = TransactionType.valueOf(reference.split("_")[0]);
//        log.info("transactionType {} from reference {}", transactionType, reference);
//
//        switch (transactionType) {
//            case GIVING:
//                Optional<Giving> optGiving = givingRepository.findByReference(reference);
//                return optGiving.orElse(new Giving());
////                break;
//            case SUBSCRIPTION:
//                Optional<Subscription> optSubscription = subscriptionRepository.findByReference(reference);
//                return optSubscription.orElse(new Subscription());
////                break;
//            default:
//                break;
//        }
//        return null;
//    }
//
//
//}
