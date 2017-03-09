package com.warrantix.main.common.rest.request;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class AddProductToCartRequest{

    @SerializedName("cartID")
    @Expose
    private String cartID;
    @SerializedName("productID")
    @Expose
    private String productID;
    @SerializedName("customerID")
    @Expose
    private String customerID;
    @SerializedName("quantity")
    @Expose
    private Number quantity;

    /**
     *
     * @return
     * The cartID
     */
    public String getCartID() {
        return cartID;
    }

    /**
     *
     * @param cartID
     * The type
     */
    public void setCartID(String cartID) {
        this.cartID = cartID;
    }

    /**
     *
     * @return
     * The productID
     */
    public String getProductID() {
        return productID;
    }

    /**
     *
     * @param productID
     * The productID
     */
    public void setProductID(String productID) {
        this.productID = productID;
    }

    /**
     *
     * @return
     * The customerID
     */
    public String getCustomerID() {
        return customerID;
    }

    /**
     *
     * @param customerID
     * The customerID
     */
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    /**
     *
     * @return
     * The quantity
     */
    public Number getQuantity() {
        return quantity;
    }

    /**
     *
     * @param quantity
     * The quantity
     */
    public void setQuantity(Number quantity) {
        this.quantity = quantity;
    }

}