package com.warrantix.main.common.event;

import com.warrantix.main.common.rest.response.RevealAddResponse;
import com.warrantix.main.common.rest.response.SearchProductResponse;

public class AddRevealSuccessEvent
{
    private RevealAddResponse revealAddResponse = new RevealAddResponse();

    public AddRevealSuccessEvent(RevealAddResponse revealAddResponse)
    {
        this.revealAddResponse = revealAddResponse;
    }

    public RevealAddResponse getRevealAddResponse()
    {
        return revealAddResponse;
    }

}
