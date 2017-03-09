package com.warrantix.main.common.rest.response;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class CustomerLoginResponse extends ErrorMessageResponse {

    @SerializedName("JWT")
    @Expose
    private String jWT;
    @SerializedName("walletID")
    @Expose
    private String walletID;
    @SerializedName("customerID")
    @Expose
    private String customerID;

    /**
     *
     * @return
     * The jWT
     */
    public String getJWT() {
        return jWT;
    }

    /**
     *
     * @param jWT
     * The JWT
     */
    public void setJWT(String jWT) {
        this.jWT = jWT;
    }

    /**
     *
     * @return
     * The walletID
     */
    public String getWalletID() {
        return walletID;
    }

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

}