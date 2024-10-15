package com.mobilebackendfinancials.financial.repository;

import com.mobilebackendfinancials.financial.model.entity.Order;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRespository extends JpaRepository<Order, Long> {
    Optional<List<Order>> findAllByUserId(@NonNull String userId);
}
