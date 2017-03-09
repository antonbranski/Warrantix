
package com.warrantix.main.common.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class MsrpCurrency {

    @SerializedName("msrpCurrency")
    @Expose
    private String msrpCurrency;

    /**
     * @return The msrpCurrency
     */
    public String getMsrpCurrency() {
        return msrpCurrency;
    }

    /**
     * @param msrpCurrency The msrpCurrency
     */
    public void setMsrpCurrency(String msrpCurrency) {
        this.msrpCurrency = msrpCurrency;
    }

}