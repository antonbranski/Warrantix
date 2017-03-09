package com.warrantix.main.common.event;

import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.common.rest.response.GetUsedProductsResponse;

import java.util.ArrayList;
import java.util.List;

public class UsedProductElectronicsSuccessEvent
{
    private GetUsedProductsResponse mGetUsedProductResponse = new GetUsedProductsResponse();
    private List<Product> mProducts = new ArrayList<>();

    public UsedProductElectronicsSuccessEvent(GetUsedProductsResponse getUsedProductsResponse, List<Product> products)
    {
        mGetUsedProductResponse = getUsedProductsResponse;
        mProducts = products;
    }

    public GetUsedProductsResponse getUsedProductsResponse()
    {
        return mGetUsedProductResponse;
    }

    public List<Product> getProducts (){
        return mProducts;
    }

}
