package com.example.cst438_group5_project1;

import androidx.room.Ignore;

import com.google.gson.annotations.SerializedName;

public class Post {
    //Declaring Initial Variables
    private String id;

    private String title;

    private String company;

    private String type;

    private String location;

    private String description;

    @Ignore
    public Post(String id, String title, String company, String type, String location) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.type = type;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany(){
        return company;
    }

    public String getType(){
        return type;
    }

    public String getLocation(){
        return location;
    }

    public String getDescription(){
        return description;
    }



}
