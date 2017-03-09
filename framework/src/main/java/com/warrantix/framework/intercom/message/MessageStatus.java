package com.warrantix.framework.intercom.message;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class MessageStatus {

    @SerializedName("success")
    @Expose
    private boolean status;

    /**
     *
     * @return
     * The status
     */
    public boolean getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

}