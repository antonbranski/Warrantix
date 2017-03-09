package com.warrantix.main.common.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;
import io.realm.annotations.PrimaryKey;

@Generated("org.jsonschema2pojo")
public class Product {

    @PrimaryKey
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("brandID")
    @Expose
    private String brandID;
    @SerializedName("class")
    @Expose
    private String _class;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("shortDescription")
    @Expose
    private String shortDescription;
    @SerializedName("serialNumberStart")
    @Expose
    private String serialNumberStart;
    @SerializedName("serialNumberEnd")
    @Expose
    private String serialNumberEnd;
    @SerializedName("loading")
    @Expose
    private String loading;
    @SerializedName("manualUrl")
    @Expose
    private String manualUrl;
    @SerializedName("manualImageThmb")
    @Expose
    private String manualImageThmb;
    @SerializedName("imageMainUrl")
    @Expose
    private String imageThmb;
    @SerializedName("mpn")
    @Expose
    private String mpn;
    @SerializedName("productionDate")
    @Expose
    private String productionDate;
    @SerializedName("releaseDate")
    @Expose
    private String releaseDate;
    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("featureAndDetails")
    @Expose
    private String featureAndDetails;
    @SerializedName("msrp")
    @Expose
    private Integer msrp;
    @SerializedName("msrpCurrency")
    @Expose
    private MsrpCurrency msrpCurrency;
    @SerializedName("msrpCurrencySymb")
    @Expose
    private String msrpCurrencySymb;
    @SerializedName("featured")
    @Expose
    private Boolean featured;
    @SerializedName("popular")
    @Expose
    private Boolean popular;
    @SerializedName("reviews")
    @Expose
    private List<Review> reviews = new ArrayList<>();
    @SerializedName("rate")
    @Expose
    private List<Rate> rate = new ArrayList<>();
    @SerializedName("mechanicalFixes")
    @Expose
    private List<String> mechanicalFixes;
    @SerializedName("engineFixes")
    @Expose
    private List<String> engineFixes;
    @SerializedName("electricalFixes")
    @Expose
    private List<String> electricalFixes;
    @SerializedName("imageUrl")
    @Expose
    private List<String> imageUrls;


    /**
     * Additional
     */
    private int quantity = 1;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The _id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt The createdAt
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return The updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt The updatedAt
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * @return The brandID
     */
    public String getBrandID() {
        return brandID;
    }

    /**
     * @param brandID The brandID
     */
    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    /**
     * @return The _class
     */
    public String getClass_() {
        return _class;
    }

    /**
     * @param _class The class
     */
    public void setClass_(String _class) {
        this._class = _class;
    }

    /**
     * @return The category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category The category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model The model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return The shortDescription
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * @param shortDescription The shortDescription
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /**
     * @return The serialNumberStart
     */
    public String getSerialNumberStart() {
        return serialNumberStart;
    }

    /**
     * @param serialNumberStart The serialNumberStart
     */
    public void setSerialNumberStart(String serialNumberStart) {
        this.serialNumberStart = serialNumberStart;
    }

    /**
     * @return The serialNumberEnd
     */
    public String getSerialNumberEnd() {
        return serialNumberEnd;
    }

    /**
     * @param serialNumberEnd The serialNumberEnd
     */
    public void setSerialNumberEnd(String serialNumberEnd) {
        this.serialNumberEnd = serialNumberEnd;
    }

    /**
     * @return The loading
     */
    public String getLoading() {
        return loading;
    }

    /**
     * @param loading The loading
     */
    public void setLoading(String loading) {
        this.loading = loading;
    }

    /**
     * @return The manualUrl
     */
    public String getManualUrl() {
        return manualUrl;
    }

    /**
     * @param manualUrl The manualUrl
     */
    public void setManualUrl(String manualUrl) {
        this.manualUrl = manualUrl;
    }

    /**
     * @return The manualImageThmb
     */
    public String getManualImageThmb() {
        return manualImageThmb;
    }

    /**
     * @param manualImageThmb The manualImageThmb
     */
    public void setManualImageThmb(String manualImageThmb) {
        this.manualImageThmb = manualImageThmb;
    }

    /**
     * @return The imageThmb
     */
    public String getImageThmb() {
        return imageThmb;
    }

    /**
     * @param imageThmb The imageThmb
     */
    public void setImageThmb(String imageThmb) {
        this.imageThmb = imageThmb;
    }

    /**
     * @return The mpn
     */
    public String getMpn() {
        return mpn;
    }

    /**
     * @param mpn The mpn
     */
    public void setMpn(String mpn) {
        this.mpn = mpn;
    }

    /**
     * @return The productionDate
     */
    public String getProductionDate() {
        return productionDate;
    }

    /**
     * @param productionDate The productionDate
     */
    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    /**
     * @return The releaseDate
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * @param releaseDate The releaseDate
     */
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * @return The sku
     */
    public String getSku() {
        return sku;
    }

    /**
     * @param sku The sku
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The featureAndDetails
     */
    public String getFeatureAndDetails() {
        return featureAndDetails;
    }

    /**
     * @param featureAndDetails The featureAndDetails
     */
    public void setFeatureAndDetails(String featureAndDetails) {
        this.featureAndDetails = featureAndDetails;
    }

    /**
     * @return The msrp
     */
    public Integer getMsrp() {
        return msrp;
    }

    /**
     * @param msrp The msrp
     */
    public void setMsrp(Integer msrp) {
        this.msrp = msrp;
    }

    /**
     * @return The msrpCurrency
     */
    public MsrpCurrency getMsrpCurrency() {
        return msrpCurrency;
    }

    /**
     * @param msrpCurrency The msrpCurrency
     */
    public void setMsrpCurrency(MsrpCurrency msrpCurrency) {
        this.msrpCurrency = msrpCurrency;
    }

    /**
     * @return The msrpCurrencySymb
     */
    public String getMsrpCurrencySymb() {
        return msrpCurrencySymb;
    }

    /**
     * @param msrpCurrencySymb The msrpCurrencySymb
     */
    public void setMsrpCurrencySymb(String msrpCurrencySymb) {
        this.msrpCurrencySymb = msrpCurrencySymb;
    }

    /**
     * @return The featured
     */
    public Boolean getFeatured() {
        return featured;
    }

    /**
     * @param featured The featured
     */
    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    /**
     * @return The popular
     */
    public Boolean getPopular() {
        return popular;
    }

    /**
     * @param popular The popular
     */
    public void setPopular(Boolean popular) {
        this.popular = popular;
    }

    /**
     * @return The reviews
     */
    public List<Review> getReviews() {
        return reviews;
    }

    /**
     * @param reviews The reviews
     */
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    /**
     * @return The rate
     */
    public List<Rate> getRate() {
        return rate;
    }

    /**
     * @param rate The rate
     */
    public void setRate(List<Rate> rate) {
        this.rate = rate;
    }

    /**
     *
     * @return
     * The id
     */
    public List<String> getMechanicalFixes() {
        return mechanicalFixes;
    }

    /**
     *
     * @param mechanicalFixes
     * The mechanicalFixes
     */
    public void setMechanicalFixes(List<String> mechanicalFixes) {
        this.mechanicalFixes = mechanicalFixes;
    }

    /**
     *
     * @return
     * The engineFixes
     */
    public List<String> getEngineFixes() {
        return engineFixes;
    }

    /**
     *
     * @param engineFixes
     * The engineFixes
     */
    public void setEngineFixes(List<String> engineFixes) {
        this.engineFixes = engineFixes;
    }

    /**
     *
     * @return
     * The electricalFixes
     */
    public List<String> getElectricalFixes() {
        return electricalFixes;
    }

    /**
     *
     * @param electricalFixes
     * The electricalFixes
     */
    public void setElectricalFixes(List<String> electricalFixes) {
        this.electricalFixes = electricalFixes;
    }
    /**
     *
     * @return
     * The imageUrls
     */
    public List<String> getImageUrls() {
        return imageUrls;
    }

    /**
     *
     * @param imageUrls
     * The imageUrls
     */
    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }


}
