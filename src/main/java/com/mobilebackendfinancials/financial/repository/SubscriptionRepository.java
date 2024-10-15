package com.mobilebackendfinancials.financial.repository;

import com.mobilebackendfinancials.financial.constant.SubscriptionStatus;
import com.mobilebackendfinancials.financial.constant.TransactionStatus;
import com.mobilebackendfinancials.financial.model.entity.Subscription;
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
