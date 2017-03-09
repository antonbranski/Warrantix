package com.warrantix.main.common.rest.request;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class WalletAddCustomerRequest {

    @SerializedName("walletID")
    @Expose
    private String walletID;

    @SerializedName("customerID")
    @Expose
    private String customerID;


    /**
     *
     * @param walletID
     * The walletID
     */
    public void setWalletID(String walletID) {
        this.walletID = walletID;
    }

    /**
     *
     * @param customerID
     * The customerID
     */
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

}