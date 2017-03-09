package com.warrantix.main.common.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Rate {

    @SerializedName("customerID")
    @Expose
    private String customerID;
    @SerializedName("review")
    @Expose
    private Integer rate;

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
     * @return The rate
     */
    public Integer getRate() {
        return rate;
    }

    /**
     * @param rate The rate
     */
    public void setRate(Integer rate) {
        this.rate = rate;
    }

}