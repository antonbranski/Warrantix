package com.warrantix.main.common.rest.response;

import com.warrantix.main.common.rest.model.Cart;
import com.warrantix.main.common.rest.model.Dealer;
import com.warrantix.main.common.rest.model.Order;
import com.warrantix.main.common.rest.model.Product;

import java.util.List;

public class GetCartsResponse extends ErrorMessageResponse {

    List<Cart> mCarts;

    public void setCarts(List<Cart> carts){
        mCarts = carts;
    }

    public List<Cart> getCarts(){
        return mCarts;
    }

}