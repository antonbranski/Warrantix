package com.warrantix.main.common.event;

import com.warrantix.main.common.rest.response.RateAddResponse;

public class AddRateSuccessEvent
{
    private RateAddResponse rateAddResponse = new RateAddResponse();

    public AddRateSuccessEvent(RateAddResponse rateAddResponse)
    {
        this.rateAddResponse = rateAddResponse;
    }

    public RateAddResponse getRateAddResponse()
    {
        return rateAddResponse;
    }

}
