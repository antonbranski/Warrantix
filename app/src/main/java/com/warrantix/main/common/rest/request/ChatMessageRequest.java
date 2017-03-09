
package com.warrantix.main.common.rest.request;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.warrantix.main.common.rest.model.RoleInfo;

@Generated("org.jsonschema2pojo")
public class ChatMessageRequest {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("to")
    @Expose
    private RoleInfo to;
    @SerializedName("from")
    @Expose
    private RoleInfo from;

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
     * The from
     */
    public RoleInfo getFrom() {
        return from;
    }

    /**
     *
     * @param from
     * The from
     */
    public void setFrom(RoleInfo from) {
        this.from = from;
    }

}