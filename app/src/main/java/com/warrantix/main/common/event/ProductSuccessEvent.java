package com.warrantix.main.common.event;

import com.warrantix.main.common.rest.model.Customer;
import com.warrantix.main.common.rest.response.GetProductResponse;
import com.warrantix.main.common.rest.response.GetRelatedProductResponse;

import java.util.ArrayList;
import java.util.List;

public class ProductSuccessEvent
{
    private GetProductResponse mGetProductResponse = new GetProductResponse();
    private List<Customer> mCustomersByRate = new ArrayList<>();
    private List<Customer> mCustomersByReview = new ArrayList<>();

    public ProductSuccessEvent(GetProductResponse getProductResponse, List<Customer> customersByReview,List<Customer> customersByrate)
    {
        mGetProductResponse = getProductResponse;
        mCustomersByRate = customersByrate;
        mCustomersByReview = customersByReview;
    }

    public GetProductResponse getProductResponse()
    {
        return mGetProductResponse;
    }

    public List<Customer> getCustomersByRate()
    {
        return mCustomersByRate;
    }

    public List<Customer> getCustomersByReview()
    {
        return mCustomersByReview;
    }


}