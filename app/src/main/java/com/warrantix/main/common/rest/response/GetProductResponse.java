package com.warrantix.main.common.rest.response;

import com.warrantix.main.common.rest.model.Product;

public class GetProductResponse extends ErrorMessageResponse {

    private Product mProduct;

    public void setProduct(Product product){
        mProduct = product;
    }

    public Product getProduct(){
        return mProduct;
    }

}