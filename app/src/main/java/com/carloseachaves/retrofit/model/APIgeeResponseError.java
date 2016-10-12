package com.carloseachaves.retrofit.model;

import com.google.gson.annotations.SerializedName;

public class APIgeeResponseError {

    @SerializedName("error")
    private String error;

    @SerializedName("exception")
    private String exception;

    @SerializedName("error_description")
    private String errorDescription;

    @SerializedName("duration")
    private int duration;

    @SerializedName("timestamp")
    private long  timestamp;

    private int code;
    private String message;


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
