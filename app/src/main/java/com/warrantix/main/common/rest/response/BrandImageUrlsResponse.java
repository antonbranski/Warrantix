package com.warrantix.main.common.rest.response;

import java.util.List;

public class BrandImageUrlsResponse extends ErrorMessageResponse {

    List<String> mBrandImgUrls;

    public void setBrandImgUrls(List<String> brandImgUrls){
        mBrandImgUrls = brandImgUrls;
    }

    public List<String> getBrandImgUrls(){
        return mBrandImgUrls;
    }

}