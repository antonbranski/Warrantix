package com.warrantix.main.common.rest.request;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class DeviceUpdateGCMTokenRequest {

    @SerializedName("token")
    @Expose
    private String token;

    /**
     *
     * @param token
     * The token
     */
    public void setToken(String token) {
        this.token = token;
    }

}