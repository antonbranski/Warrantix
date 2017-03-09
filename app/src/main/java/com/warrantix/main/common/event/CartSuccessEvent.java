package com.warrantix.main.common.event;


import com.warrantix.main.common.rest.response.GetCartResponse;

public class CartSuccessEvent
{
    private GetCartResponse mCartResponse = new GetCartResponse();

    public CartSuccessEvent(GetCartResponse getCartResponse)
    {
        mCartResponse = getCartResponse;
    }

    public GetCartResponse getCartResponse()
    {
        return mCartResponse;
    }

}
