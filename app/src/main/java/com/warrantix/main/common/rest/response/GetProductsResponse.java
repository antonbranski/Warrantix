package com.warrantix.main.common.rest.response;

import com.warrantix.main.common.rest.model.Product;

import java.util.List;

public class GetProductsResponse extends ErrorMessageResponse {

    List<Product> mProducts;

    public void setProducts(List<Product> products){
        mProducts = products;
    }

    public List<Product> getProducts(){
        return mProducts;
    }

}