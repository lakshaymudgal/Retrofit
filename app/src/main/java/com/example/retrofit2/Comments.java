package com.example.retrofit2;

import com.google.gson.annotations.SerializedName;

public class Comments {

    private int postId;
    private int id;
    private String email;
    private String title;
    @SerializedName("body")
    private String text;

    public int getPostId() {
        return postId;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
