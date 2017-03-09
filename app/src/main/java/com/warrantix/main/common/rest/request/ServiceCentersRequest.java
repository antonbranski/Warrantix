package com.warrantix.main.common.rest.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class ServiceCentersRequest {

    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("latitude")
    @Expose
    private String latitude;


    /**
     *
     * @return
     * The name
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setLongitude(String type) {
        this.longitude = longitude;
    }

    /**
     *
     * @return
     * The brandID
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     *
     * @param brandID
     * The brandID
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

}