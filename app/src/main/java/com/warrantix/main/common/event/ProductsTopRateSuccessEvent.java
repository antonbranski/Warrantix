package com.warrantix.main.common.event;

import com.warrantix.main.common.rest.response.GetProductsResponse;

public class ProductsTopRateSuccessEvent
{
    private GetProductsResponse mGetProductResponse = new GetProductsResponse();

    public ProductsTopRateSuccessEvent(GetProductsResponse getProductsResponse)
    {
        mGetProductResponse = getProductsResponse;
    }

    public GetProductsResponse getProductsResponse()
    {
        return mGetProductResponse;
    }

}
