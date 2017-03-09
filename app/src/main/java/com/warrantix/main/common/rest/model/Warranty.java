package com.warrantix.main.common.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Warranty {

    @SerializedName("transferee")
    @Expose
    private String transferee;
    @SerializedName("walletID")
    @Expose
    private String walletID;
    @SerializedName("emailAddress")
    @Expose
    private String emailAddress;

    @SerializedName("mobileNumber")
    @Expose
    private String mobileNumber;

    @SerializedName("customerID")
    @Expose
    private String customerID;
    @SerializedName("transferDate")
    @Expose
    private String transferDate;
    @SerializedName("transferMessage")
    @Expose
    private String transferMessage;
    @SerializedName("warrantyStyle")
    @Expose
    private Boolean warrantyStyle;

    /**
     *
     * @return
     * The transferee
     */
    public String getTransferee() {
        return transferee;
    }

    /**
     *
     * @param transferee
     * The transferee
     */
    public void setTransferee(String transferee) {
        this.transferee = transferee;
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
     * The emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     *
     * @param emailAddress
     * The emailAddress
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     *
     * @return
     * The mobileNumber
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     *
     * @param mobileNumber
     * The mobileNumber
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
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
     * The transferDate
     */
    public String getTransferDate() {
        return transferDate;
    }

    /**
     *
     * @param transferDate
     * The transferDate
     */
    public void setTransferDate(String transferDate) {
        this.transferDate = transferDate;
    }

    /**
     *
     * @return
     * The transferMessage
     */
    public String getTransferMessage() {
        return transferMessage;
    }

    /**
     *
     * @param transferMessage
     * The transferMessage
     */
    public void setTransferMessage(String transferMessage) {
        this.transferMessage = transferMessage;
    }

    /**
     *
     * @return
     * The warrantyStyle
     */
    public Boolean getWarrantyStyle() {
        return warrantyStyle;
    }

    /**
     *
     * @param warrantyStyle
     * The warrantyStyle
     */
    public void setWarrantyStyle(Boolean warrantyStyle) {
        this.warrantyStyle = warrantyStyle;
    }


}