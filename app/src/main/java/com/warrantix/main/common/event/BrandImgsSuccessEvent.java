package com.warrantix.main.common.event;

import com.warrantix.main.common.rest.response.BrandImageUrlsResponse;

public class BrandImgsSuccessEvent {
    private BrandImageUrlsResponse mBrandImageUrlsResponse = new BrandImageUrlsResponse();

    public BrandImgsSuccessEvent(BrandImageUrlsResponse brandImageUrlsResponse) {
        mBrandImageUrlsResponse = brandImageUrlsResponse;
    }

    public BrandImageUrlsResponse getBrandImageUrlsResponse() {
        return mBrandImageUrlsResponse;
    }

}
