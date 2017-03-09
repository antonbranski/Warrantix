package com.warrantix.main.common.rest.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Order {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("cartID")
    @Expose
    private String cartID;
    @SerializedName("customerID")
    @Expose
    private String customerID;
    @SerializedName("contact")
    @Expose
    private Contact contact;
    @SerializedName("productID")
    @Expose
    private String productID;
    @SerializedName("quantity")
    @Expose
    private Number quantity;
    @SerializedName("unitPrice")
    @Expose
    private Number unitPrice;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("deliveryCharges")
    @Expose
    private Number deliveryCharges;
    @SerializedName("totalAmount")
    @Expose
    private Number totalAmount;
    @SerializedName("deliveryDate")
    @Expose
    private String deliveryDate;
    @SerializedName("status")
    @Expose
    private String status;

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The _id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     *
     * @param createdAt
     * The createdAt
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     *
     * @return
     * The updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     *
     * @param updatedAt
     * The updatedAt
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

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
     * The cartID
     */
    public void setCartID(String cartID) {
        this.cartID = cartID;
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
     * The contact
     */
    public Contact getContact() {
        return contact;
    }

    /**
     *
     * @param contact
     * The contact
     */
    public void setContact(Contact contact) {
        this.contact = contact;
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

    /**
     *
     * @return
     * The unitPrice
     */
    public Number getUnitPrice() {
        return unitPrice;
    }

    /**
     *
     * @param unitPrice
     * The unitPrice
     */
    public void setUnitPrice(Number unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     *
     * @return
     * The currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     *
     * @param currency
     * The currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     *
     * @return
     * The deliveryCharges
     */
    public Number getDeliveryCharges() {
        return deliveryCharges;
    }

    /**
     *
     * @param deliveryCharges
     * The deliveryCharges
     */
    public void setDeliveryCharges(Number deliveryCharges) {
        this.deliveryCharges = deliveryCharges;
    }

    /**
     *
     * @return
     * The totalAmount
     */
    public Number getTotalAmount() {
        return totalAmount;
    }

    /**
     *
     * @param totalAmount
     * The totalAmount
     */
    public void setTotalAmount(Number totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     *
     * @return
     * The deliveryDate
     */
    public String getDeliveryDate() {
        return deliveryDate;
    }

    /**
     *
     * @param deliveryDate
     * The deliveryDate
     */
    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

}