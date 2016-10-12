package com.carloseachaves.retrofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class APIgeeResponseSuccess {

    //TODO any object
    @SerializedName("entities")
    private List<Book> entities;

    @SerializedName("action")
    private String action;

    @SerializedName("application")
    private String application;

    @SerializedName("path")
    private String path;

    @SerializedName("uri")
    private String uri;

    @SerializedName("timestamp")
    private String timestamp;

    @SerializedName("duration")
    private int duration;

    @SerializedName("organization")
    private String organization;

    @SerializedName("applicationName")
    private String applicationName;

    @SerializedName("count")
    private int count;

    public APIgeeResponseSuccess(List<Book> entities, String action, String application, String path, String uri, String timestamp, int duration, String organization, String applicationName, int count) {
        this.entities = entities;
        this.action = action;
        this.application = application;
        this.path = path;
        this.uri = uri;
        this.timestamp = timestamp;
        this.duration = duration;
        this.organization = organization;
        this.applicationName = applicationName;
        this.count = count;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Book> getEntities() {
        return entities;
    }

    public void setEntities(List<Book> entities) {
        this.entities = entities;
    }
}

