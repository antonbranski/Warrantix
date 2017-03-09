package com.warrantix.main.common.rest.model;

/**
 * Created on 10/07/16.
 */
public class Amount {

    private float value;
    private String currency;

    public Amount() {
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
