package com.warrantix.main.common.localdb;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created on 19/07/16.
 */
public class BrandObject extends RealmObject {

    @PrimaryKey
    private String brandId;

    private String brandName;
    private String brandIcon;
    private int brandOrder;
    private int brandTextIcon;
    private int brandTextSelectedIcon;
    private boolean isLeadBrand;
    private boolean isDefault;
    //For Brand Tab
    @Ignore
    private boolean showUnderline = false;
    @Ignore
    private boolean isSelected = false;

    public BrandObject() {
    }

    /**
     *
     * @param brandId
     * @param brandName
     * @param brandIcon
     * @param brandOrder
     * @param brandTextIcon
     * @param brandTextSelectedIcon
     * @param isLeadBrand
     * @param isDefault
     * @param showUnderline
     * @param isSelected
     */
    public BrandObject(String brandId, String brandName, String brandIcon, int brandOrder, int brandTextIcon, int brandTextSelectedIcon, boolean isLeadBrand, boolean isDefault, boolean showUnderline, boolean isSelected) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.brandIcon = brandIcon;
        this.brandOrder = brandOrder;
        this.brandTextIcon = brandTextIcon;
        this.brandTextSelectedIcon = brandTextSelectedIcon;
        this.isLeadBrand = isLeadBrand;
        this.isDefault = isDefault;
        this.showUnderline = showUnderline;
        this.isSelected = isSelected;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandIcon() {
        return brandIcon;
    }

    public void setBrandIcon(String brandIcon) {
        this.brandIcon = brandIcon;
    }

    public int getBrandOrder() {
        return brandOrder;
    }

    public void setBrandOrder(int brandOrder) {
        this.brandOrder = brandOrder;
    }

    public int getBrandTextIcon() {
        return brandTextIcon;
    }

    public void setBrandTextIcon(int brandTextIcon) {
        this.brandTextIcon = brandTextIcon;
    }

    public int getBrandTextSelectedIcon() {
        return brandTextSelectedIcon;
    }

    public void setBrandTextSelectedIcon(int brandTextSelectedIcon) {
        this.brandTextSelectedIcon = brandTextSelectedIcon;
    }

    public boolean isLeadBrand() {
        return isLeadBrand;
    }

    public void setLeadBrand(boolean leadBrand) {
        isLeadBrand = leadBrand;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public boolean isShowUnderline() {
        return showUnderline;
    }

    public void setShowUnderline(boolean showUnderline) {
        this.showUnderline = showUnderline;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
