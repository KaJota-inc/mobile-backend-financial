package com.mobilebackendfinancial.financial.service;

import com.mobilebackendfinancial.financial.constant.SubscriptionStatus;
import com.mobilebackendfinancial.financial.constant.SubscriptionType;
import com.mobilebackendfinancial.financial.constant.TransactionStatus;
import com.mobilebackendfinancial.financial.model.dto.request.FetchLiveSubscriptionDto;
import com.mobilebackendfinancial.financial.model.entity.Subscription;
import com.mobilebackendfinancial.financial.model.extDto.request.PaystackVerifyDto;
import com.mobilebackendfinancial.financial.repository.SubscriptionRepository;
import com.mobilebackendfinancial.financial.util.Misc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    SubscriptionRepository subscriptionRepository;
    @Autowired
    PaystackBreakService paystackBreakService;

    public Subscription addSubscriptionTransaction(Subscription subscription) {
        log.info("Trying to add SUBSCRIPTION transaction");

        Subscription savedSubscription = subscriptionRepository.save(subscription);

        log.info("savedSubscription:  {}", new JSONObject(savedSubscription));
        return savedSubscription;

    }

    public Subscription updateSubscriptionTransaction(PaystackVerifyDto.ResponseDto paystackData) {
        log.info("Trying to update SUBSCRIPTION transaction");

        BigDecimal realAmount = new BigDecimal(paystackData.getData().getAmount()).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);

        String callbackStatus = Misc.getStatusFromEvent(paystackData.getEvent());
        TransactionStatus status = TransactionStatus.valueOf(callbackStatus);

        SubscriptionStatus subscriptionStatus = status.equals(TransactionStatus.SUCCESS) ?
                SubscriptionStatus.ACTIVE :
                SubscriptionStatus.DISABLED;

        PaystackVerifyDto.DataDto callbackData = paystackData.getData();
        Optional<Subscription> optSubscription = subscriptionRepository.findByReference(callbackData.getReference());

        Subscription subscription = optSubscription.orElse(new Subscription());
        Long subscriptionId = subscription.getId();
        String userId = subscription.getUserId();
        modelMapper.map(callbackData, subscription);
        subscription
                .setId(subscriptionId)
                .setUserId(userId)
                .setAmount(String.valueOf(realAmount))
                .setStatus(status)
                .setSubscriptionStatus(subscriptionStatus)
                .setExpiryDate(Misc.getNextExpiryDate(subscription.getSubscriptionType()))
                .setEmail(callbackData.getCustomer().getEmail())
                .setPhone(callbackData.getCustomer().getPhone())
                .setPaidAt(callbackData.getPaid_at())
                .setCreatedAt(callbackData.getCreated_at());

        Subscription savedSubscription = subscriptionRepository.save(subscription);

        log.info("savedSubscription:  {}", new JSONObject(savedSubscription));
        return savedSubscription;

    }

    public FetchLiveSubscriptionDto.ResponseDto fetchLiveSubscription(String userId) {
        log.info("Trying to fetch Live subscription");

        annulExpiredSubscription(userId);
        FetchLiveSubscriptionDto.ResponseDto response = new FetchLiveSubscriptionDto.ResponseDto();


        List<Subscription> activeSubscriptions = subscriptionRepository.findByUserIdAndSubscriptionStatus(userId, SubscriptionStatus.ACTIVE);
        log.info("activeSubscriptions:: {}", new JSONArray(activeSubscriptions));

        List<Subscription> pendingSubscriptions = subscriptionRepository.findByUserIdAndStatus(userId, TransactionStatus.PENDING);
        log.info("pendingSubscriptions:: {}", new JSONArray(pendingSubscriptions));

        List<Subscription> newActiveSubscriptions = pendingSubscriptions.stream().map(
                        subscription -> {
                            try {
                                PaystackVerifyDto.ResponseDto responseDto = paystackBreakService.verifyPaystackPayment(subscription.getReference());
                                PaystackVerifyDto.DataDto paystackData = responseDto.getData();
                                BigDecimal realAmount = new BigDecimal(paystackData.getAmount()).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);

                                TransactionStatus status = TransactionStatus.valueOf(paystackData.getStatus());

                                SubscriptionStatus subscriptionStatus = status.equals(TransactionStatus.SUCCESS) ?
                                        SubscriptionStatus.ACTIVE :
                                        SubscriptionStatus.DISABLED;

                                Long subscriptionId = subscription.getId();
                                modelMapper.map(paystackData, subscription);
                                subscription
                                        .setId(subscriptionId)
                                        .setUserId(userId)
                                        .setAmount(String.valueOf(realAmount))
                                        .setStatus(status)
                                        .setExpiryDate(Misc.getNextExpiryDate(subscription.getSubscriptionType()))
                                        .setSubscriptionStatus(subscriptionStatus)
                                        .setEmail(paystackData.getCustomer().getEmail())
                                        .setPhone(paystackData.getCustomer().getPhone())
                                        .setPaidAt(paystackData.getPaid_at())
                                        .setCreatedAt(paystackData.getCreated_at());
                                subscription = subscriptionRepository.save(subscription);
                                log.info("subscription:  {}", new JSONObject(subscription));
                            } catch (Exception ex) {
                                log.info("Error while trying to verifying payment:: {}", ex.getMessage());

                            }
                            return subscription;
                        }
                ).filter(subscription -> TransactionStatus.SUCCESS.equals(subscription.getStatus())
                        && SubscriptionStatus.ACTIVE.equals(subscription.getSubscriptionStatus()))
                .collect(Collectors.toList());

        newActiveSubscriptions.addAll(activeSubscriptions);


        BigDecimal totalAmountPaid = new BigDecimal(0);
        long noOfMonths = 0;

        newActiveSubscriptions.sort(Comparator.comparing(Subscription::getCreatedDate));

        log.info("newActiveSubscriptions:: {}", new JSONArray(newActiveSubscriptions));

        Subscription latestSubscription = newActiveSubscriptions.stream().findFirst().orElse(new Subscription());
        LocalDateTime oldestSubscriptionExpiryDate = latestSubscription.getExpiryDate();
        SubscriptionType oldestSubscriptionType = latestSubscription.getSubscriptionType();

        for (Subscription subscription : newActiveSubscriptions) {
            totalAmountPaid = totalAmountPaid.add(new BigDecimal(subscription.getAmount()));
            modelMapper.map(subscription, latestSubscription);
            noOfMonths += subscription.getSubscriptionType().getNoOfMonths();
        }

        LocalDateTime mainDateOfNextSubscription = oldestSubscriptionExpiryDate
                .plusMonths(noOfMonths - oldestSubscriptionType.getNoOfMonths());

        response
                .setSubscriptionType(latestSubscription.getSubscriptionType())
                .setSubscriptionStatus(latestSubscription.getSubscriptionStatus())
                .setCurrency(latestSubscription.getCurrency())
                .setPaymentMethod(latestSubscription.getChannel())
                .setAmountPaid(totalAmountPaid)
                .setDateOfNextSubscription(mainDateOfNextSubscription)
        ;

        log.info("response:  {}", new JSONObject(response));
        return response;

    }

    public void annulExpiredSubscription(String userId) {
        log.info("Trying to annul expired subscription");
        List<Subscription> activeSubscriptions = subscriptionRepository.findByUserIdAndSubscriptionStatus(userId, SubscriptionStatus.ACTIVE);

        activeSubscriptions.forEach(
                subscription -> {
                    if (LocalDateTime.now().isAfter(subscription.getExpiryDate())) {
                        subscription.setSubscriptionStatus(SubscriptionStatus.EXPIRED);
                    }
                    subscriptionRepository.save(subscription);
                }
        );

    }

}
