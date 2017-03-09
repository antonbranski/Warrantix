package com.warrantix.main.common.event;

import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.common.rest.response.GetRelatedProductsResponse;

import java.util.ArrayList;
import java.util.List;

public class RelatedProductsSuccessEvent
{
    private GetRelatedProductsResponse mGetRelatedProductResponse = new GetRelatedProductsResponse();
    private List<Product> mProducts = new ArrayList<>();

    public RelatedProductsSuccessEvent(GetRelatedProductsResponse getRelatedProductsResponse, List<Product> products)
    {
        mGetRelatedProductResponse = getRelatedProductsResponse;
        mProducts = products;
    }

    public GetRelatedProductsResponse getRelatedProductsResponse()
    {
        return mGetRelatedProductResponse;
    }

    public List<Product> getProducts (){
        return mProducts;
    }

}
