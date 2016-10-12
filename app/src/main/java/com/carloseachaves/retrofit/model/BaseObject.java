package com.carloseachaves.retrofit.model;

import com.google.gson.annotations.SerializedName;

public class BaseObject {

    @SerializedName("uuid")
    private String uuid;

    @SerializedName("type")
    private String type;

    @SerializedName("created")
    private long created;

    @SerializedName("modified")
    private long modified;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public long getModified() {
        return modified;
    }

    public void setModified(long modified) {
        this.modified = modified;
    }
}
