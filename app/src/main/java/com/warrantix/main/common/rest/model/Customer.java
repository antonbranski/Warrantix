package com.warrantix.main.common.rest.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.warrantix.main.common.rest.response.ErrorMessageResponse;

@Generated("org.jsonschema2pojo")
public class Customer {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("customerID")
    @Expose
    private String customerID;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("challenge")
    @Expose
    private String challenge;
    @SerializedName("salt")
    @Expose
    private String salt;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("contact")
    @Expose
    private Contact contact;
    @SerializedName("vault")
    @Expose
    private String vault;
    @SerializedName("leadBrand")
    @Expose
    private String leadBrand;
    @SerializedName("brandID")
    @Expose
    private List<String> brandID;
    @SerializedName("deviceID")
    @Expose
    private List<String> deviceID;
    private HashMap<String, Device> devices;

    @SerializedName("walletID")
    @Expose
    private String walletID;
    @SerializedName("facebookID")
    @Expose
    private String facebookID;
    @SerializedName("friends")
    @Expose
    private List<String> friends;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("image")
    @Expose
    private String image;

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
     * The username
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     * The username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     * The password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     * The password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     * The challenge
     */
    public String getChallenge() {
        return challenge;
    }

    /**
     *
     * @param challenge
     * The challenge
     */
    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    /**
     *
     * @return
     * The salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     *
     * @param salt
     * The salt
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     *
     * @return
     * The role
     */
    public String getRole() {
        return role;
    }

    /**
     *
     * @param role
     * The role
     */
    public void setRole(String role) {
        this.role = role;
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
     * The vault
     */
    public String getVault() {
        return vault;
    }

    /**
     *
     * @param vault
     * The vault
     */
    public void setVault(String vault) {
        this.vault = vault;
    }

    /**
     *
     * @return
     * The leadBrand
     */
    public String getLeadBrand() {
        return leadBrand;
    }

    /**
     *
     * @param leadBrand
     * The leadBrand
     */
    public void setLeadBrand(String leadBrand) {
        this.leadBrand = leadBrand;
    }

    /**
     *
     * @return
     * The brandID
     */
    public List<String> getBrandID() {
        return brandID;
    }

    /**
     *
     * @param brandID
     * The brandID
     */
    public void setBrandID(List<String> brandID) {
        this.brandID = brandID;
    }

    /**
     *
     * @return
     * The deviceID
     */
    public List<String> getDeviceID() {
        return deviceID;
    }

    /**
     *
     * @param deviceID
     * The deviceID
     */
    public void setDeviceID(List<String> deviceID) {
        this.deviceID = deviceID;
    }

    /**
     *
     * @param newDeviceId
     * The deviceID
     */
    public void addDeviceID(String newDeviceId) {
        if (this.deviceID == null)
            this.deviceID = new ArrayList<String>();

        this.deviceID.add(newDeviceId);
    }

    /**
     *
     * @param removeDeviceId
     * The deviceID
     */
    public void removeDeviceID(String removeDeviceId) {
        if (this.deviceID == null)
            return;

        this.deviceID.remove(removeDeviceId);
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
     * The facebookID
     */
    public String getFacebookID() {
        return facebookID;
    }

    /**
     *
     * @param facebookID
     * The facebookID
     */
    public void setFacebookID(String facebookID) {
        this.facebookID = facebookID;
    }

    /**
     *
     * @return
     * The friends
     */
    public List<String> getFriends() {
        return friends;
    }

    /**
     *
     * @param friends
     * The friends
     */
    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    /**
     *
     * @return
     * The status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }


    public void addDevice(String deviceID, Device device) {
        if (this.devices == null)
            this.devices = new HashMap<>();

        this.devices.put(deviceID, device);
    }

    public void removeDevice(String deviceID) {
        if (this.devices == null)
            return;

        this.devices.remove(deviceID);
    }

    public Device getDevice(String deviceID) {
        if (this.devices == null)
            return null;

        return this.devices.get(deviceID);
    }

    /**
     *
     * @return
     * The image
     */
    public String getImage() {
        return image;
    }

    /**
     *
     * @param image
     * The image
     */
    public void setImage(String image) {
        this.image = image;
    }


}