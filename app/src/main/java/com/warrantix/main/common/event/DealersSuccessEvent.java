package com.warrantix.main.common.event;

import com.warrantix.main.common.rest.response.GetDealersResponse;

public class DealersSuccessEvent {
    private GetDealersResponse mGetDealersResponse = new GetDealersResponse();

    public DealersSuccessEvent(GetDealersResponse getDealersResponse) {
        mGetDealersResponse = getDealersResponse;
    }

    public GetDealersResponse getDealersResponse() {
        return mGetDealersResponse;
    }

}
