package com.warrantix.main.common.rest.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class ReminderMessageContent {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("sub")
    @Expose
    private String sub;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("imageThumb")
    @Expose
    private String imageThumb;

    /**
     *
     * @return
     * The brandId
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param brandId
     * The brandId
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The brandName
     */
    public String getSub() {
        return sub;
    }

    /**
     *
     * @param brandName
     * The brandName
     */
    public void setSub(String sub) {
        this.sub = sub;
    }

    /**
     *
     * @return
     * The webviewUrl
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param webviewUrl
     * The webviewUrl
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The imageUrl
     */
    public String getImageThumb() {
        return imageThumb;
    }

    /**
     *
     * @param imageUrl
     * The imageUrl
     */
    public void setImageThumb(String imageThumb) {
        this.imageThumb = imageThumb;
    }

}