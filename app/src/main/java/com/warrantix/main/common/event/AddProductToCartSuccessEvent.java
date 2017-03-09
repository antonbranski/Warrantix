package com.warrantix.main.common.event;

import com.warrantix.main.common.rest.response.AddProductToCartResponse;

public class AddProductToCartSuccessEvent
{
    private AddProductToCartResponse addProductToCartResponse = new AddProductToCartResponse();

    public AddProductToCartSuccessEvent(AddProductToCartResponse addProductToCartResponse)
    {
        this.addProductToCartResponse = addProductToCartResponse;
    }

    public AddProductToCartResponse getAddProductToCartResponse()
    {
        return addProductToCartResponse;
    }

}
