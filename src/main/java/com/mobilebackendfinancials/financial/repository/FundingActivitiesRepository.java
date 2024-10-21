package com.mobilebackendfinancials.financial.repository;

import com.mobilebackendfinancials.financial.model.entity.FundingActivities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Repository
public interface FundingActivitiesRepository extends JpaRepository<FundingActivities, Long> {
    List<FundingActivities> findAllByUserId(@NotNull  String userId);
}
