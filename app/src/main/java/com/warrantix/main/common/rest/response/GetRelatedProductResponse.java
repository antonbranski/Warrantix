package com.warrantix.main.common.rest.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.warrantix.main.common.rest.model.Contact;
import com.warrantix.main.common.rest.model.RelatedProduct;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class GetRelatedProductResponse extends ErrorMessageResponse {

    RelatedProduct mRelatedproduct = new RelatedProduct();

    public void setRelatedProduct(RelatedProduct relatedProduct){
        mRelatedproduct = relatedProduct;
    }

    public RelatedProduct getRelatedProduct(){
        return mRelatedproduct;
    }

}