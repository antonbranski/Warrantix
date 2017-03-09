package com.warrantix.main.common.rest.request;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.warrantix.main.common.rest.model.RoleInfo;

@Generated("org.jsonschema2pojo")
public class RegisterProductRequest {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("brandID")
    @Expose
    private String brandID;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("serial")
    @Expose
    private String serial;
    @SerializedName("purchaseDate")
    @Expose
    private String purchaseDate;
    @SerializedName("dealerName")
    @Expose
    private String dealerName;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("currency")
    @Expose
    private String currency;

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String type) {
        this.name = name;
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
     * The model
     */
    public String getModel() {
        return model;
    }

    /**
     *
     * @param model
     * The model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     *
     * @return
     * The serial
     */
    public String getSerial() {
        return serial;
    }

    /**
     *
     * @param serial
     * The serial
     */
    public void setSerial(String serial) {
        this.serial = serial;
    }

    /**
     *
     * @return
     * The purchaseDate
     */
    public String getPurchaseDate() {
        return purchaseDate;
    }

    /**
     *
     * @param purchaseDate
     * The purchaseDate
     */
    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    /**
     *
     * @return
     * The dealerName
     */
    public String getDealerName() {
        return dealerName;
    }

    /**
     *
     * @param dealerName
     * The dealerName
     */
    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    /**
     *
     * @return
     * The price
     */
    public String getPrice() {
        return price;
    }

    /**
     *
     * @param price
     * The price
     */
    public void setPrice(String price) {
        this.price = price;
    }

}