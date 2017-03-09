package com.warrantix.main.common.event;

import com.warrantix.main.common.rest.response.GetServiceCompaniesResponse;

public class ServiceCompaniesSuccessEvent
{
    private GetServiceCompaniesResponse mGetServiceCompaniesResponse = new GetServiceCompaniesResponse();

    public ServiceCompaniesSuccessEvent(GetServiceCompaniesResponse getServiceCompaniesResponse)
    {
        mGetServiceCompaniesResponse = getServiceCompaniesResponse;
    }

    public GetServiceCompaniesResponse getServiceCompaniesResponse()
    {
        return mGetServiceCompaniesResponse;
    }

}
