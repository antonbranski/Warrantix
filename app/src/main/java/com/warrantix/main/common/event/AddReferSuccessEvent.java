package com.warrantix.main.common.event;

import com.warrantix.main.common.rest.response.ReferAddResponse;

public class AddReferSuccessEvent
{
    private ReferAddResponse referAddResponse = new ReferAddResponse();

    public AddReferSuccessEvent(ReferAddResponse referAddResponse)
    {
        this.referAddResponse = referAddResponse;
    }

    public ReferAddResponse getReferAddResponse()
    {
        return referAddResponse;
    }

}
