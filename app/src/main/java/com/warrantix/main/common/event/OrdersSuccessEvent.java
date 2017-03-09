package com.warrantix.main.common.event;

import com.warrantix.main.common.rest.model.Customer;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.common.rest.response.CustomerResponse;
import com.warrantix.main.common.rest.response.GetOrdersResponse;

import java.util.ArrayList;
import java.util.List;

public class OrdersSuccessEvent
{
    private GetOrdersResponse mGetOrdersResponse = new GetOrdersResponse();
    private List<Product> mProducts = new ArrayList<>();
    private List<CustomerResponse> mCustomers = new ArrayList<>();

    public OrdersSuccessEvent(GetOrdersResponse getOrdersResponse, List<Product> products, List<CustomerResponse> customerResponses)
    {
        mGetOrdersResponse = getOrdersResponse;
        mProducts = products;
        mCustomers = customerResponses;
    }

    public GetOrdersResponse getOrdersResponse()
    {
        return mGetOrdersResponse;
    }
    public List<Product> getProducts(){
        return mProducts;
    }

    public List<CustomerResponse> getCustomers(){
        return mCustomers;
    }

}
