package com.warrantix.main.common.rest.request;

import com.warrantix.main.common.rest.model.CartItem;
import com.warrantix.main.common.rest.model.Contact;

import java.util.List;

/**
 * Created on 10/07/16.
 */
public class AddCartRequest {

    private String customerID;
    private Contact deliveryAddress;
    private String currency;
    private List<CartItem> cartItems;

    public AddCartRequest() {
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public Contact getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Contact deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
