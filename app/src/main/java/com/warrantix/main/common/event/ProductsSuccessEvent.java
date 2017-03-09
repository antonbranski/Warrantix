package com.warrantix.main.common.event;

        import com.warrantix.main.common.rest.response.GetProductsResponse;

public class ProductsSuccessEvent
{
    private GetProductsResponse mGetProductResponse = new GetProductsResponse();

    public ProductsSuccessEvent(GetProductsResponse getProductsResponse)
    {
        mGetProductResponse = getProductsResponse;
    }

    public GetProductsResponse getProductsResponse()
    {
        return mGetProductResponse;
    }

}
