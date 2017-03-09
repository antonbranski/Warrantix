package com.warrantix.main.common.rest.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class UsedProduct {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("productID")
    @Expose
    private String productID;
    @SerializedName("brandID")
    @Expose
    private String brandID;
    @SerializedName("customerID")
    @Expose
    private String customerID;
    @SerializedName("warrantyID")
    @Expose
    private String warrantyID;
    @SerializedName("msrp")
    @Expose
    private Number msrp;
    @SerializedName("msrpCurrency")
    @Expose
    private MsrpCurrency msrpCurrency;
    @SerializedName("msrpCurrencySymb")
    @Expose
    private String msrpCurrencySymb;

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
     * The brandID
     */
    public String getBrandID() {
        return brandID;
    }

    /**
     *
     * @param brandID
     * The brandID
     */
    public void setBrandID(String brandID) {
        this.brandID = brandID;
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
     * The warrantyID
     */
    public String getWarrantyID() {
        return warrantyID;
    }

    /**
     *
     * @param warrantyID
     * The warrantyID
     */
    public void setWarrantyID(String warrantyID) {
        this.warrantyID = warrantyID;
    }

    /**
     *
     * @return
     * The msrp
     */
    public Number getMsrp() {
        return msrp;
    }

    /**
     *
     * @param msrp
     * The msrp
     */
    public void setMsrp(Number msrp) {
        this.msrp = msrp;
    }

    /**
     *
     * @return
     * The msrpCurrency
     */
    public MsrpCurrency getMsrpCurrency() {
        return msrpCurrency;
    }

    /**
     *
     * @param msrpCurrency
     * The msrpCurrency
     */
    public void setMsrpCurrency(MsrpCurrency msrpCurrency) {
        this.msrpCurrency = msrpCurrency;
    }

    /**
     *
     * @return
     * The msrpCurrencySymb
     */
    public String getMsrpCurrencySymb() {
        return msrpCurrencySymb;
    }

    /**
     *
     * @param msrpCurrencySymb
     * The msrpCurrencySymb
     */
    public void setMsrpCurrencySymb(String msrpCurrencySymb) {
        this.msrpCurrencySymb = msrpCurrencySymb;
    }

}