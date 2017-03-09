package com.warrantix.main.common.rest.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Cart {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("customerID")
    @Expose
    private String customerID;
    @SerializedName("deliveryAddress")
    @Expose
    private Contact deliveryAddress;
    @SerializedName("totalAmount")
    @Expose
    private Number totalAmount;
    @SerializedName("currency")
    @Expose
    private String currency;


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
     * The brandID
     */
    public String getCustomerID() {
        return customerID;
    }

    /**
     *
     * @param brandID
     * The brandID
     */
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    /**
     *
     * @return
     * The contact
     */
    public Contact getDeliveryAddress() {
        return deliveryAddress;
    }

    /**
     *
     * @param contact
     * The contact
     */
    public void setDeliveryAddress(Contact deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    /**
     *
     * @return
     * The name
     */
    public Number getTotalAmount() {
        return totalAmount;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setTotalAmount(Number totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     *
     * @return
     * The imagelUrl
     */
    public String getCurrency() {
        return currency;
    }

    /**
     *
     * @param imagelUrl
     * The imagelUrl
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

}