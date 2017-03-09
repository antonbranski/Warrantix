package com.warrantix.framework.intercom.message;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class PluginInformation extends MessageStatus {

    @SerializedName("brandapp")
    @Expose
    private String brandapp;

    @SerializedName("retailerapp")
    @Expose
    private String retailerapp;

    @SerializedName("showicon")
    @Expose
    private boolean showicon;


    /**
     *
     * @return
     * The showicon
     */
    public boolean getShowIcon() {
        return showicon;
    }

    /**
     *
     * @param showicon
     * The showicon
     */
    public void setShowIcon(boolean showicon) {
        this.showicon = showicon;
    }


    /**
     *
     * @return
     * The brandapp
     */
    public String getBrandApp() {
        return brandapp;
    }

    /**
     *
     * @param brandApp
     * The brandapp
     */
    public void setBrandApp(String brandApp) {
        this.brandapp = brandApp;
    }

    /**
     *
     * @return
     * The retailerapp
     */
    public String getRetailerApp() {
        return retailerapp;
    }

    /**
     *
     * @param retailerApp
     * The retailerapp
     */
    public void setRetailerApp(String retailerApp) {
        this.retailerapp = retailerApp;
    }

}