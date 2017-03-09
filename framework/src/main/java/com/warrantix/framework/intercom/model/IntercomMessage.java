package com.warrantix.framework.intercom.model;

import android.graphics.Bitmap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class IntercomMessage {

    @SerializedName("to")
    @Expose
    private String toWhere;

    @SerializedName("from")
    @Expose
    private String fromWhere;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("message")
    @Expose
    private String message;

    /**
     *
     * @return
     * The toWhere
     */
    public String getToWhere() {
        return toWhere;
    }

    /**
     *
     * @param toWhere
     * The toWhere
     */
    public void setToWhere(String toWhere) {
        this.toWhere = toWhere;
    }

    /**
     *
     * @return
     * The fromWhere
     */
    public String getFromWhere() {
        return fromWhere;
    }

    /**
     *
     * @param fromWhere
     * The fromWhere
     */
    public void setFromWhere(String fromWhere) {
        this.fromWhere = fromWhere;
    }

    /**
     *
     * @return
     * The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     * The message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The message
     */
    public void setMessage(String message) {
        this.message = message;
    }


}