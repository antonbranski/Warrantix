package com.warrantix.main.common.rest.response;

import com.warrantix.main.common.rest.model.RelatedProduct;

import java.util.List;

public class GetRelatedProductsResponse extends ErrorMessageResponse {

    List<RelatedProduct> mRelatedProducts;

    public void setRelatedProducts(List<RelatedProduct> relatedProducts){
        mRelatedProducts = relatedProducts;
    }

    public List<RelatedProduct> getRelatedProducts(){
        return mRelatedProducts;
    }

}