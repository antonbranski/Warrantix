package com.warrantix.main.common.rest.response;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.warrantix.main.common.rest.model.Customer;
import com.warrantix.main.common.rest.model.Device;

@Generated("org.jsonschema2pojo")
public class DeviceResponse extends Device {

    @SerializedName("code")
    @Expose
    private Integer code = 0;

    @SerializedName("message")
    @Expose
    private String message = "";

    /**
     *
     * @return
     * The code
     */
    public Integer getCode() {
        return code;
    }

    /**
     *
     * @param code
     * The code
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     *
     * @return
     * The message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}