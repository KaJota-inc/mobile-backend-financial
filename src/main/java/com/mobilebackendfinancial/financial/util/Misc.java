package com.mobilebackendfinancial.financial.util;

import com.mobilebackendfinancial.financial.constant.SubscriptionType;
import com.mobilebackendfinancial.financial.constant.TransactionStatus;

import java.time.LocalDateTime;
import java.util.Locale;


public class Misc {
    public static String getStatusFromEvent(String event) {
        if (event.contains(TransactionStatus.PENDING.getMessage().toLowerCase(Locale.ROOT))) {
            return TransactionStatus.PENDING.getMessage();
        }
        if (event.contains(TransactionStatus.SUCCESS.getMessage().toLowerCase(Locale.ROOT))) {
            return TransactionStatus.SUCCESS.getMessage();
        }
        if (event.contains(TransactionStatus.FAILED.getMessage().toLowerCase(Locale.ROOT))) {
            return TransactionStatus.FAILED.getMessage();
        }
        return TransactionStatus.DISABLE.getMessage();
    }


    public static LocalDateTime getNextExpiryDate(SubscriptionType type) {
        if (type.equals(SubscriptionType.ANNUALLY)) {
            return LocalDateTime.now().plusMonths(12);
        }
        if (type.equals(SubscriptionType.QUARTERLY)) {
            return LocalDateTime.now().plusMonths(3);
        }
        if (type.equals(SubscriptionType.MONTHLY)) {
            return LocalDateTime.now().plusMonths(1);
        }
        return LocalDateTime.now();
    }
}
