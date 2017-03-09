package com.warrantix.main.common.rest.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.warrantix.main.common.rest.model.MsrpCurrency;

@Generated("org.jsonschema2pojo")
public class Deal {

    @SerializedName("productID")
    @Expose
    private String productID;
    @SerializedName("brandID")
    @Expose
    private String brandID;
    @SerializedName("price")
    @Expose
    private Number price;
    @SerializedName("currency")
    @Expose
    private MsrpCurrency currency;
    @SerializedName("shortDescription")
    @Expose
    private String shortDescription;

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
     * The price
     */
    public Number getPrice() {
        return price;
    }

    /**
     *
     * @param price
     * The price
     */
    public void setPrice(Number price) {
        this.price = price;
    }

    /**
     *
     * @return
     * The currency
     */
    public MsrpCurrency getCurrency() {
        return currency;
    }

    /**
     *
     * @param currency
     * The currency
     */
    public void setCurrency(MsrpCurrency currency) {
        this.currency = currency;
    }

    /**
     *
     * @return
     * The shortDescription
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     *
     * @param shortDescription
     * The shortDescription
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

}