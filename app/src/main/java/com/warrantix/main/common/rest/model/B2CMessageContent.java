package com.warrantix.main.common.rest.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class B2CMessageContent {

    @SerializedName("brandId")
    @Expose
    private String brandId;
    @SerializedName("brandName")
    @Expose
    private String brandName;
    @SerializedName("webviewUrl")
    @Expose
    private String webviewUrl;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;

    /**
     *
     * @return
     * The brandId
     */
    public String getBrandId() {
        return brandId;
    }

    /**
     *
     * @param brandId
     * The brandId
     */
    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    /**
     *
     * @return
     * The brandName
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     *
     * @param brandName
     * The brandName
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     *
     * @return
     * The webviewUrl
     */
    public String getWebviewUrl() {
        return webviewUrl;
    }

    /**
     *
     * @param webviewUrl
     * The webviewUrl
     */
    public void setWebviewUrl(String webviewUrl) {
        this.webviewUrl = webviewUrl;
    }

    /**
     *
     * @return
     * The imageUrl
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     *
     * @param imageUrl
     * The imageUrl
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}