package com.warrantix.main.common.event;

import com.warrantix.main.common.rest.model.Customer;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.common.rest.response.CustomerResponse;
import com.warrantix.main.common.rest.response.PullMessageResponse;

import java.util.ArrayList;
import java.util.List;

public class MessagesSuccessEvent
{
    private PullMessageResponse mPullMessageResponse = new PullMessageResponse();
    private List<Product> mProducts = new ArrayList<>();
    private List<CustomerResponse> mCustomerResponse = new ArrayList<>();

    public MessagesSuccessEvent(PullMessageResponse pullMessageResponse, List<Product> products, List<CustomerResponse> customerResponses)
    {
        mPullMessageResponse = pullMessageResponse;
        mCustomerResponse = customerResponses;
        mProducts = products;
    }

    public PullMessageResponse getPullMessageResponse()
    {
        return mPullMessageResponse;
    }

    public List<Product> getProducts()
    {
        return mProducts;
    }

    public List<CustomerResponse> getCustomerResponses()
    {
        return mCustomerResponse;
    }

}
