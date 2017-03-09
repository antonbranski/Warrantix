package com.warrantix.main.common.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class ServiceCenter {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("brandID")
    @Expose
    private String brandID;
    @SerializedName("contact")
    @Expose
    private Contact contact;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("imagelUrl")
    @Expose
    private String imagelUrl;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("rate")
    @Expose
    private Number rate;
    @SerializedName("availability")
    @Expose
    private List<String> availability;


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
     * The contact
     */
    public Contact getContact() {
        return contact;
    }

    /**
     *
     * @param contact
     * The contact
     */
    public void setContact(Contact contact) {
        this.contact = contact;
    }

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
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The imagelUrl
     */
    public String getImagelUrl() {
        return imagelUrl;
    }

    /**
     *
     * @param imagelUrl
     * The imagelUrl
     */
    public void setImagelUrl(String imagelUrl) {
        this.imagelUrl = imagelUrl;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The rate
     */
    public Number getRate() {
        return rate;
    }

    /**
     *
     * @param rate
     * The rate
     */
    public void setRate(Number rate) {
        this.rate = rate;
    }


    /**
     *
     * @return
     * The availability
     */
    public List<String> getAvailability() {
        return availability;
    }

    /**
     *
     * @param availability
     * The availability
     */
    public void setAvailability(List<String> availability) {
        this.availability = availability;
    }

}

