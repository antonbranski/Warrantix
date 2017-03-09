package com.warrantix.main.modules.intercom.model;

public class BrandTypeData {

    private String brandName;
    private byte[] brandIconByteArray;

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandName() {
        return this.brandName;
    }

    public void setBrandIconByteArray(byte[] byteArray) {
        brandIconByteArray = byteArray;
    }

    public byte[] getBrandIconByteArray() {
        return this.brandIconByteArray;
    }

}
