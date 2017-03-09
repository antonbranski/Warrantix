package com.warrantix.main.common.rest.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Contact {

    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("tel")
    @Expose
    private String tel;
    @SerializedName("ssn")
    @Expose
    private String ssn;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("zip")
    @Expose
    private Integer zip;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("contactName")
    @Expose
    private String contactName;
    @SerializedName("contactEmail")
    @Expose
    private String contactEmail;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("Latitude")
    @Expose
    private Double latitude;
    @SerializedName("Longitude")
    @Expose
    private Double longitude;

    /**
     *
     * @return
     * The firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     * The firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     * The lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     * The lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     * The tel
     */
    public String getTel() {
        return tel;
    }

    /**
     *
     * @param tel
     * The tel
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     *
     * @return
     * The ssn
     */
    public String getSsn() {
        return ssn;
    }

    /**
     *
     * @param ssn
     * The ssn
     */
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    /**
     *
     * @return
     * The address
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     * The address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return
     * The city
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     * The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return
     * The zip
     */
    public Integer getZip() {
        return zip;
    }

    /**
     *
     * @param zip
     * The zip
     */
    public void setZip(Integer zip) {
        this.zip = zip;
    }

    /**
     *
     * @return
     * The country
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country
     * The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return
     * The contactName
     */
    public String getContactName() {
        return contactName;
    }

    /**
     *
     * @param contactName
     * The contactName
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     *
     * @return
     * The contactEmail
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     *
     * @param contactEmail
     * The contactEmail
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    /**
     *
     * @return
     * The website
     */
    public String getWebsite() {
        return website;
    }

    /**
     *
     * @param website
     * The website
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     *
     * @return
     * The latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude
     * The Latitude
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return
     * The longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude
     * The Longitude
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

}