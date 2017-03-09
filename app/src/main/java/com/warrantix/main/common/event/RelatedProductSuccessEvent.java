package com.warrantix.main.common.event;

import com.warrantix.main.common.rest.response.GetRelatedProductResponse;

public class RelatedProductSuccessEvent
{
    private GetRelatedProductResponse mGetRelatedProductResponse = new GetRelatedProductResponse();

    public RelatedProductSuccessEvent(GetRelatedProductResponse getRelatedProductResponse)
    {
        mGetRelatedProductResponse = getRelatedProductResponse;
    }

    public GetRelatedProductResponse getRelatedProductsResponse()
    {
        return mGetRelatedProductResponse;
    }

}
