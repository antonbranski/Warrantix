package com.warrantix.main.common.rest.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class RateMessageContent {

    @SerializedName("customerID")
    @Expose
    private String customerID;
    @SerializedName("rate")
    @Expose
    private String rate;

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
     * The content
     */
    public String getRate() {
        return rate;
    }

    /**
     *
     * @param content
     * The content
     */
    public void setRate(String rate) {
        this.rate = rate;
    }


}
