package com.warrantix.main.common.event;

import com.warrantix.main.common.rest.response.GetProductsResponse;

public class ProductsBestSellingSuccessEvent
{
    private GetProductsResponse mGetProductResponse = new GetProductsResponse();

    public ProductsBestSellingSuccessEvent(GetProductsResponse getProductsResponse)
    {
        mGetProductResponse = getProductsResponse;
    }

    public GetProductsResponse getProductsResponse()
    {
        return mGetProductResponse;
    }

}
