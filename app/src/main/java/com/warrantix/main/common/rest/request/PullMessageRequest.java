package com.warrantix.main.common.rest.request;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.warrantix.main.common.rest.model.RoleInfo;

@Generated("org.jsonschema2pojo")
public class PullMessageRequest {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("to")
    @Expose
    private RoleInfo to;
    @SerializedName("since")
    @Expose
    private String since;

    /**
     *
     * @return
     * The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     * The to
     */
    public RoleInfo getTo() {
        return to;
    }

    /**
     *
     * @param to
     * The to
     */
    public void setTo(RoleInfo to) {
        this.to = to;
    }

    /**
     *
     * @return
     * The since
     */
    public String getSince() {
        return since;
    }

    /**
     *
     * @param since
     * The since
     */
    public void setSince(String since) {
        this.since = since;
    }

}