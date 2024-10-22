package com.mobilebackendfinancials.financial.service;

import com.mobilebackendfinancials.financial.constant.ResponseCodes;
import com.mobilebackendfinancials.financial.exception.GenericException;
import com.mobilebackendfinancials.financial.model.dto.request.AddFundingActivitiesDto;
import com.mobilebackendfinancials.financial.model.dto.request.FetchFundingActivitiesDto;
import com.mobilebackendfinancials.financial.model.entity.FundingActivities;
import com.mobilebackendfinancials.financial.model.factory.FundingActivitiesFactory;
import com.mobilebackendfinancials.financial.repository.FundingActivitiesRepository;
import com.mobilebackendfinancials.financial.service.ServiceInterfaces.IFundingActivitiesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FundingActivitiesService implements IFundingActivitiesService {

    @Autowired
    FundingActivitiesRepository fundingActivitiesRepository;

    @Override
    public AddFundingActivitiesDto.ResponseDto addFundingActivities(AddFundingActivitiesDto.RequestDto requestDto) {

        try {
            FundingActivities newFundingActivity = FundingActivitiesFactory.fromDto(requestDto);
            fundingActivitiesRepository.save(newFundingActivity);
            return new AddFundingActivitiesDto.ResponseDto().setMessage("Successfully added new funding activity");
        } catch (Exception err) {
            log.info("Something went wrong while adding activity:{}", err.getMessage());
        }
        return null;
    }

    @Override
    public FetchFundingActivitiesDto.ResponseDto fetchFundingActivities(FetchFundingActivitiesDto.RequestDto requestDto) {
        try{
            List<FundingActivities> fundingActivities = fundingActivitiesRepository.findAllByUserId(requestDto.getUserId());
            return new FetchFundingActivitiesDto.ResponseDto().setFundingActivities(fundingActivities);
        }catch (Exception ex){
            log.info("Something went wrong while fetching funding activities:{}", ex.getMessage());
        }
        return null;
    }
}
