package com.mobilebackendfinancials.financial.service.ServiceInterfaces;

import com.mobilebackendfinancials.financial.model.dto.request.AddFundingActivitiesDto;
import com.mobilebackendfinancials.financial.model.dto.request.FetchFundingActivitiesDto;

public interface IFundingActivitiesService {
    AddFundingActivitiesDto.ResponseDto addFundingActivities(AddFundingActivitiesDto.RequestDto requestDto);
    FetchFundingActivitiesDto.ResponseDto fetchFundingActivities(FetchFundingActivitiesDto.RequestDto requestDto);
}
