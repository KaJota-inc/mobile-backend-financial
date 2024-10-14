package com.mobilebackendfinancial.financial.repository;

import com.mobilebackendfinancial.financial.constant.SubscriptionStatus;
import com.mobilebackendfinancial.financial.constant.TransactionStatus;
import com.mobilebackendfinancial.financial.model.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByUserIdAndStatus(@NonNull String userId, @NonNull TransactionStatus status);
    List<Subscription> findByUserIdAndSubscriptionStatus(@NonNull String userId, @NonNull SubscriptionStatus subscriptionStatus);
    Optional<Subscription> findByReference(String reference);

}
