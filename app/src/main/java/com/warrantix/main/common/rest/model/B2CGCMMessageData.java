package com.warrantix.main.common.rest.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class B2CGCMMessageData {

    @SerializedName("command")
    @Expose
    private String command;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("apsUrl")
    @Expose
    private String apsUrl;
    @SerializedName("importance")
    @Expose
    private String importance;

    /**
     *
     * @return
     * The command
     */
    public String getCommand() {
        return command;
    }

    /**
     *
     * @param command
     * The command
     */
    public void setCommand(String command) {
        this.command = command;
    }

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
     * The apsUrl
     */
    public String getApsUrl() {
        return apsUrl;
    }

    /**
     *
     * @param apsUrl
     * The apsUrl
     */
    public void setApsUrl(String apsUrl) {
        this.apsUrl = apsUrl;
    }

    /**
     *
     * @return
     * The importance
     */
    public String getImportance() {
        return importance;
    }

    /**
     *
     * @param importance
     * The importance
     */
    public void setImportance(String importance) {
        this.importance = importance;
    }

}