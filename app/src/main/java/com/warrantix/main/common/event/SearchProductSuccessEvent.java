package com.warrantix.main.common.event;

import com.warrantix.main.common.rest.response.SearchProductResponse;

public class SearchProductSuccessEvent
{
    private SearchProductResponse mSearchProductResponse = new SearchProductResponse();

    public SearchProductSuccessEvent(SearchProductResponse searchProductResponse)
    {
        mSearchProductResponse = searchProductResponse;
    }

    public SearchProductResponse searchProductResponse()
    {
        return mSearchProductResponse;
    }

}
