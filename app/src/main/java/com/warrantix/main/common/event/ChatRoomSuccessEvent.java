package com.warrantix.main.common.event;

import com.warrantix.main.common.rest.model.Customer;
import com.warrantix.main.common.rest.model.LatestMessage;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.common.rest.response.CustomerResponse;
import com.warrantix.main.common.rest.response.PullMessageResponse;

import java.util.ArrayList;
import java.util.List;

public class ChatRoomSuccessEvent
{
    private List<LatestMessage> mLatestMessages = new ArrayList<>();
    private List<CustomerResponse> mCustomerResponse = new ArrayList<>();

    public ChatRoomSuccessEvent(List<LatestMessage> latestMessages, List<CustomerResponse> customerResponses)
    {
        mLatestMessages = latestMessages;
        mCustomerResponse = customerResponses;
    }

    public List<LatestMessage> getLatestMessages()
    {
        return mLatestMessages;
    }


    public List<CustomerResponse> getCustomerResponses()
    {
        return mCustomerResponse;
    }

}
