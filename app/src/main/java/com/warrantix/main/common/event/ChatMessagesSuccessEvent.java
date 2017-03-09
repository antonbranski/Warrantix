package com.warrantix.main.common.event;

import com.warrantix.main.common.rest.model.Customer;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.common.rest.response.CustomerResponse;
import com.warrantix.main.common.rest.response.PullMessageResponse;

import java.util.ArrayList;
import java.util.List;

public class ChatMessagesSuccessEvent
{
    private PullMessageResponse mPullMessageResponse = new PullMessageResponse();
    private List<CustomerResponse> mCustomerResponse = new ArrayList<>();

    public ChatMessagesSuccessEvent(PullMessageResponse pullMessageResponse, List<CustomerResponse> customerResponses)
    {
        mPullMessageResponse = pullMessageResponse;
        mCustomerResponse = customerResponses;
    }

    public PullMessageResponse getPullMessageResponse()
    {
        return mPullMessageResponse;
    }


    public List<CustomerResponse> getCustomerResponses()
    {
        return mCustomerResponse;
    }

}
