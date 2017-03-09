package com.warrantix.main.common.rest.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class ReviewMessageContent {

    @SerializedName("customerID")
    @Expose
    private String customerID;
    @SerializedName("content")
    @Expose
    private String content;

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
    public String getContent() {
        return content;
    }

    /**
     *
     * @param content
     * The content
     */
    public void setContent(String content) {
        this.content = content;
    }


}
