package com.warrantix.main.common.rest.response;

import com.warrantix.main.common.rest.model.Message;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.common.rest.model.UsedProduct;

import java.util.List;

public class GetUsedProductsResponse extends ErrorMessageResponse {

    List<UsedProduct> mUsedProducts;

    public void setUsedProducts(List<UsedProduct> usedProducts){
        mUsedProducts = usedProducts;
    }

    public List<UsedProduct> getUsedProducts(){
        return mUsedProducts;
    }

}