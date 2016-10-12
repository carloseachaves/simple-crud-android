package com.carloseachaves.retrofit.model;

import com.google.gson.annotations.SerializedName;

public class Book extends BaseObject {

    @SerializedName("name")
    private String name;

    @SerializedName("author")
    private String author;

    @SerializedName("year")
    private int year;

    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}




