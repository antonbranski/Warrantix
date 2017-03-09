package com.warrantix.main.common.event;

import com.warrantix.main.common.rest.response.GetServiceCenterResponse;

public class ServiceCenterSuccessEvent
{
    private GetServiceCenterResponse mGetServiceCenterResponse = new GetServiceCenterResponse();

    public ServiceCenterSuccessEvent(GetServiceCenterResponse getServiceCenterResponse)
    {
        mGetServiceCenterResponse = getServiceCenterResponse;
    }

    public GetServiceCenterResponse getServiceCenterResponse()
    {
        return mGetServiceCenterResponse;
    }

}
