package com.warrantix.main.common.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Review {

    @SerializedName("customerID")
    @Expose
    private String customerID;
    @SerializedName("review")
    @Expose
    private String review;

    /**
     * @return The customerID
     */
    public String getCustomerID() {
        return customerID;
    }

    /**
     * @param customerID The customerID
     */
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    /**
     * @return The review
     */
    public String getReview() {
        return review;
    }

    /**
     * @param review The review
     */
    public void setReview(String review) {
        this.review = review;
    }

}