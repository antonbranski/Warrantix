package com.warrantix.main.common.rest.response;

import com.warrantix.main.common.rest.model.Cart;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class GetCartResponse extends ErrorMessageResponse {

    Cart mCart = new Cart();

    public void setCart(Cart cart){
        mCart = cart;
    }

    public Cart getCart(){
        return mCart;
    }

}