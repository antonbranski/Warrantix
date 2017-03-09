package com.warrantix.main.common.rest.model;

/**
 * Created on 10/07/16.
 */
public class CartItem {

    private String _id;
    private String productId;
    private int quantity;
    private float price;
    private String brandId;


    public CartItem() {
    }

    public CartItem(String _id, String productId, int quantity, float price, String brandId) {
        this._id = _id;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.brandId = brandId;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }
}
