package com.example.stockproject.Activities.model;
/**
 * This class is a model for the RecyclerView to use.
 */
public class FollowersModel {
    String followerName;
    int image;

    /**
     * This constructor simply assigns the values of the user.
     * @param followerName The name of the follower
     * @param image The profile picture of the follower
     */
    public FollowersModel(String followerName, int image) {
        this.followerName = followerName;
        this.image = image;
    }
    /**
     * This methods returns the name of the follower in this model.
     * @return a String followerName
     */
    public String getFollowerName() {
        return followerName;
    }
    /**
     * This methods returns the profile picture of the follower in this model.
     * @return an int image
     */
    public int getImage() {
        return image;
    }
}
