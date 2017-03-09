package com.warrantix.main.common.rest.response;

import com.warrantix.main.common.rest.model.Amount;

/**
 * Created on 10/07/16.
 */
public class CitrusBillResponse extends ErrorMessageResponse {
    private String merchantTxnId;
    private Amount amount;
    private String requestSignature;
    private String merchantAccessKey;
    private String returnUrl;

    public CitrusBillResponse() {
    }

    public String getMerchantTxnId() {
        return merchantTxnId;
    }

    public void setMerchantTxnId(String merchantTxnId) {
        this.merchantTxnId = merchantTxnId;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public String getRequestSignature() {
        return requestSignature;
    }

    public void setRequestSignature(String requestSignature) {
        this.requestSignature = requestSignature;
    }

    public String getMerchantAccessKey() {
        return merchantAccessKey;
    }

    public void setMerchantAccessKey(String merchantAccessKey) {
        this.merchantAccessKey = merchantAccessKey;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
}
