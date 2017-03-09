package com.warrantix.main.common.rest.request;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class CustomerChangePasswordRequest{

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("oldPassword")
    @Expose
    private String oldPassword;
    @SerializedName("newPassword")
    @Expose
    private String newPassword;

    /**
     *
     * @param email
     * The email
     */
    public void setEmail(String email) {
        this.email = email;
    }


    /**
     *
     * @param oldPassword
     * The old Password
     */
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    /**
     *
     * @param newPassword
     * The new Password
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }


}