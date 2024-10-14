package com.mobilebackendfinancial.financial.repository;

import com.mobilebackendfinancial.financial.model.entity.Giving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface GivingRepository extends JpaRepository<Giving, Long> {
    List<Giving> findByUserId(@NonNull String userId);
    Optional<Giving> findByReference(@NonNull String reference);

}
