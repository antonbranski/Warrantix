package com.warrantix.main.common.rest.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class B2CGCMMessageContent {

    @SerializedName("to")
    @Expose
    private String to;
    @SerializedName("data")
    @Expose
    private B2CGCMMessageData data;

    /**
     *
     * @return
     * The to
     */
    public String getTo() {
        return to;
    }

    /**
     *
     * @param to
     * The to
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     *
     * @return
     * The data
     */
    public B2CGCMMessageData getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(B2CGCMMessageData data) {
        this.data = data;
    }

}

