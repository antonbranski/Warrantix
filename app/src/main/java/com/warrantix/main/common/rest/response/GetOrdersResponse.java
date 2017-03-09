package com.warrantix.main.common.rest.response;

import com.warrantix.main.common.rest.model.Order;

import java.util.List;

public class GetOrdersResponse extends ErrorMessageResponse {

    List<Order> mOrders;

    public void setOrders(List<Order> orders){
        mOrders = orders;
    }

    public List<Order> getOrders(){
        return mOrders;
    }

}