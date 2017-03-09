package com.warrantix.main.common.rest.request;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class CustomerFacebookIDRequest{

    @SerializedName("facebookID")
    @Expose
    private String facebookID;

    /**
     *
     * @param facebookID
     * The facebookID
     */
    public void setFacebookID(String facebookID) {
        this.facebookID = facebookID;
    }


}