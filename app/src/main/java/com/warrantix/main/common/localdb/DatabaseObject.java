package com.warrantix.main.common.localdb;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DatabaseObject extends RealmObject {

    @PrimaryKey
    private String id;

    private String objectType;

    private String conditionField;

    private String jsonData;

    public DatabaseObject() {
    }

    public DatabaseObject(String id, String objectType, String conditionField, String jsonData) {
        this.id = id;
        this.objectType = objectType;
        this.conditionField = conditionField;
        this.jsonData = jsonData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getConditionField() {
        return conditionField;
    }

    public void setConditionField(String conditionField) {
        this.conditionField = conditionField;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }
}