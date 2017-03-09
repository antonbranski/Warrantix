package com.warrantix.main.common.event;

        import com.warrantix.main.common.rest.response.GetServiceCentersResponse;

public class ServiceCentersSuccessEvent
{
    private GetServiceCentersResponse mGetServiceCentersResponse = new GetServiceCentersResponse();

    public ServiceCentersSuccessEvent(GetServiceCentersResponse getServiceCentersResponse)
    {
        mGetServiceCentersResponse = getServiceCentersResponse;
    }

    public GetServiceCentersResponse getServiceCentersResponse()
    {
        return mGetServiceCentersResponse;
    }

}
