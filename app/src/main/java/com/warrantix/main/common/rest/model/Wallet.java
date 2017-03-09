package com.warrantix.main.common.rest.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.warrantix.main.common.rest.response.ErrorMessageResponse;

@Generated("org.jsonschema2pojo")
public class Wallet {

    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("walletID")
    @Expose
    private String walletID;
    @SerializedName("household")
    @Expose
    private List<String> household;
    private HashMap<String, Customer> familyMembers;

    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("primaryID")
    @Expose
    private String primaryID;
    @SerializedName("cash")
    @Expose
    private Double cash;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;

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
     * The household
     */
    public List<String> getHousehold() {
        return household;
    }

    /**
     *
     * @param household
     * The household
     */
    public void setHousehold(List<String> household) {
        this.household = household;
    }

    /**
     *
     * @return
     * The currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     *
     * @param currency
     * The currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

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
     * The primaryID
     */
    public String getPrimaryID() {
        return primaryID;
    }

    /**
     *
     * @param primaryID
     * The primaryID
     */
    public void setPrimaryID(String primaryID) {
        this.primaryID = primaryID;
    }

    /**
     *
     * @return
     * The cash
     */
    public Double getCash() {
        return cash;
    }

    /**
     *
     * @param cash
     * The cash
     */
    public void setCash(Double cash) {
        this.cash = cash;
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

    public void addFamily(String customerID, Customer customer) {
        if (this.familyMembers == null)
            this.familyMembers = new HashMap<>();

        this.familyMembers.put(customerID, customer);
    }

    public void removeFamily(String customerID) {
        if (this.familyMembers == null)
            return;

        this.familyMembers.remove(customerID);
    }

    public Customer getFamily(String customerID) {
        if (this.familyMembers == null)
            return null;

        return this.familyMembers.get(customerID);
    }

    public HashMap<String, Customer> getFamilyMembers() {
        if (this.familyMembers == null)
            return null;

        return this.familyMembers;
    }

}