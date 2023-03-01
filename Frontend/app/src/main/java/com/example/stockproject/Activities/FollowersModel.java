package com.example.stockproject.Activities;

public class FollowersModel {
    String followerName;
    int image;


    public FollowersModel(String followerName, int image) {
        this.followerName = followerName;
        this.image = image;
    }

    public String getFollowerName() {
        return followerName;
    }

    public int getImage() {
        return image;
    }
}
