package com.warrantix.main.common.event;

import com.warrantix.main.common.rest.response.GetProductsResponse;

public class ProductsPopularSuccessEvent
{
    private GetProductsResponse mGetProductResponse = new GetProductsResponse();

    public ProductsPopularSuccessEvent(GetProductsResponse getProductsResponse)
    {
        mGetProductResponse = getProductsResponse;
    }

    public GetProductsResponse getProductsResponse()
    {
        return mGetProductResponse;
    }

}
